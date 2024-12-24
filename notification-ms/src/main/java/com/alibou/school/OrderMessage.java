package com.alibou.school;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class OrderMessage {
    @Id
    @GeneratedValue
    private String orderId;
    private String productId;
    private String customerId;
    private String customerName;
    private double totalPrice;

    // Getters and Setters
}
