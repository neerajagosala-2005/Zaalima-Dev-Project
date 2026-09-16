package com.ecommerce.order_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(String orderId, String productId, int quantity) {

        String message = orderId + "," + productId + "," + quantity;

        kafkaTemplate.send("orders", orderId, message);

        System.out.println("Order event sent: " + message);
    }
}