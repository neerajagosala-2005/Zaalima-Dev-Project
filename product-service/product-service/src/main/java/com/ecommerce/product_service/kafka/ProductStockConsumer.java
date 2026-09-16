package com.ecommerce.product_service.kafka;

import jakarta.annotation.PostConstruct;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.repository.ProductRepository;

@Service
public class ProductStockConsumer {

    private final ProductRepository productRepository;

    public ProductStockConsumer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void test() {
        System.out.println("ProductStockConsumer is working!");
    }

    @KafkaListener(topics = "orders", groupId = "product-group")
    public void consume(String message) {

        System.out.println("Product Service received order: " + message);

        String[] data = message.split(",");

        Long productId = Long.parseLong(data[1]);
        int quantity = Integer.parseInt(data[2]);

        System.out.println("Product ID: " + productId);
        System.out.println("Quantity to reduce: " + quantity);

        Product product = productRepository.findById(productId).orElse(null);

        if (product != null) {

            int currentStock = product.getQuantity();

            if (currentStock >= quantity) {

                product.setQuantity(currentStock - quantity);
                productRepository.save(product);

                System.out.println("Stock updated successfully!");
                System.out.println("Remaining stock: " + product.getQuantity());

            } else {

                System.out.println("Not enough stock!");

            }

        } else {

            System.out.println("Product not found: " + productId);

        }
    }
}

