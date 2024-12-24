package com.alibou.student;

import lombok.Data;

import java.util.List;


@Data
public class OrderRequest {

    private String userId;
    private List<OrderItemRequest> items;

    // Getters et Setters
    @Data
    public static class OrderItemRequest {
        private Long productId;
        private Integer quantity;

        // Getters et Setters
    }
}
