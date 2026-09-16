package com.ecommerce.inventory_service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory getInventoryByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for product: " + productId));
    }

    public Inventory updateQuantity(Long productId, Integer quantity) {
        Inventory inventory = getInventoryByProductId(productId);
        inventory.setQuantity(quantity);
        return inventoryRepository.save(inventory);
    }

    public boolean reduceStock(Long productId, Integer quantity) {
        Inventory inventory = getInventoryByProductId(productId);

        if (inventory.getQuantity() < quantity) {
            return false;
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);

        return true;
    }
}