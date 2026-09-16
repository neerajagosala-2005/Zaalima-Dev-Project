
package com.ecommerce.notification_service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaNotificationConsumer {

    @KafkaListener(topics = "orders", groupId = "notification-group")
    public void consume(String message) {
        System.out.println("Received Order Event: " + message);
    }

    @KafkaListener(topics = "order-status", groupId = "notification-status-group")
    public void consumeStatus(String message) {
        System.out.println("Received Order Status: " + message);
    }
}

