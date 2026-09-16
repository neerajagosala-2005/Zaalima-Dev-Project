package com.ecommerce.notification_service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public String sendNotification(String message) {
        return "Notification sent: " + message;
    }
}