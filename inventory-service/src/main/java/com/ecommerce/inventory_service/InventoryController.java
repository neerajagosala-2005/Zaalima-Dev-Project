
package com.ecommerce.inventory_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Inventory> addInventory(
            @RequestBody Inventory inventory) {

        return ResponseEntity.ok(
                inventoryService.addInventory(inventory)
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> getInventory(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                inventoryService.getInventoryByProductId(productId)
        );
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Inventory> updateQuantity(
            @PathVariable Long productId,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(
                inventoryService.updateQuantity(productId, quantity)
        );
    }

    @PutMapping("/{productId}/reduce")
    public ResponseEntity<String> reduceStock(
            @PathVariable Long productId,
            @RequestParam Integer quantity) {

        boolean reduced =
                inventoryService.reduceStock(productId, quantity);

        if (reduced) {
            return ResponseEntity.ok("Stock reduced successfully");
        }

        return ResponseEntity.badRequest()
                .body("Insufficient stock");
    }
}
