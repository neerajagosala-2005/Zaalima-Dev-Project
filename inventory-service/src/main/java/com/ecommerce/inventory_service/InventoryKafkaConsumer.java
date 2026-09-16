package com.ecommerce.inventory_service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryKafkaConsumer {

    private final InventoryService inventoryService;

    public InventoryKafkaConsumer(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(
        topics = "order-created",
        groupId = "inventory-service-group"
    )
    public void consumeOrderCreated(OrderCreatedEvent event) {

        System.out.println(
            "Inventory received Order Event: "
            + event.getOrderId()
            + ", Product: "
            + event.getProductId()
            + ", Quantity: "
            + event.getQuantity()
        );

        try {
            boolean reduced = inventoryService.reduceStock(
                event.getProductId(),
                event.getQuantity()
            );

            if (reduced) {
                System.out.println(
                    "Inventory stock reduced successfully for Product: "
                    + event.getProductId()
                );
            } else {
                System.out.println(
                    "Insufficient inventory for Product: "
                    + event.getProductId()
                );
            }

        } catch (Exception e) {
            System.out.println(
                "Inventory update failed: " + e.getMessage()
            );
        }
    }
}