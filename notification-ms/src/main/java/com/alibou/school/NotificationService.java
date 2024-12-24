package com.alibou.school;


import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendEmailNotification(String customerEmail, String message) {
        // Send email logic
        System.out.println("Sending email to " + customerEmail + ": " + message);
    }

    public void sendSMSNotification(String customerPhone, String message) {
        // Send SMS logic
        System.out.println("Sending SMS to " + customerPhone + ": " + message);
    }

    public void sendPushNotification(String customerId, String message) {
        // Send push notification logic
        System.out.println("Sending push notification to customer " + customerId + ": " + message);
    }
}

