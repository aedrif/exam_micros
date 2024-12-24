package com.alibou.student;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class OrderResponse {

    private Long orderId;
    private String status;
    private LocalDateTime orderDate;
    private List<OrderItemResponse> items;

    // Getters et Setters
@Data
    public static class OrderItemResponse {
        private Long productId;
        private Integer quantity;
        private Double price;

        // Getters et Setters
    }
}
