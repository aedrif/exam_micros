package com.alibou.school;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class NotificationConsumer {

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void consumeOrderMessage(OrderMessage orderMessage) {
        // Here you can decide the type of notification to send (email, SMS, push notification, etc.)
        String notificationMessage = "Your order " + orderMessage.getOrderId() + " has been placed successfully!";
        notificationService.sendEmailNotification(orderMessage.getCustomerName(), notificationMessage);
    }
}
