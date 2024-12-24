package com.alibou.student;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final EventPublisher eventPublisher; // Pour publier des événements Kafka
    private final ProductGraphQLClient productGraphQLClient; // Pour interroger le service Produits

    public OrderService(OrderRepository orderRepository, EventPublisher eventPublisher, ProductGraphQLClient productGraphQLClient) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
        this.productGraphQLClient = productGraphQLClient;
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Vérification des stocks via GraphQL
        for (OrderRequest.OrderItemRequest item : orderRequest.getItems()) {
            boolean isAvailable = productGraphQLClient.checkProductAvailability(item.getProductId(), item.getQuantity());
            if (!isAvailable) {
                throw new IllegalArgumentException("Le produit avec ID " + item.getProductId() + " est en rupture de stock.");
            }
        }

        // Création de la commande
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");

        List<OrderItem> items = orderRequest.getItems().stream()
                .map(req -> {
                    OrderItem item = new OrderItem();
                    item.setProductId(req.getProductId());
                    item.setQuantity(req.getQuantity());
                    item.setPrice(productGraphQLClient.getProductPrice(req.getProductId()));
                    item.setOrder(order);
                    return item;
                })
                .collect(Collectors.toList());

        order.setItems(items);
        Order savedOrder = orderRepository.save(order);

        // Publier un événement dans Kafka
        eventPublisher.publishOrderCreatedEvent(savedOrder);

        // Retourner la réponse
        return mapToOrderResponse(savedOrder);
    }

    private OrderResponse mapToOrderResponse(Order order) {
        // Mapper l'entité vers la réponse DTO
        return null; // Implémenter le mapping ici
    }
}
