package com.ecommerce.product_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // CREATE PRODUCT
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // GET ALL PRODUCTS
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET PRODUCT BY ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // UPDATE PRODUCT
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());

        return productRepository.save(existingProduct);
    }

    // DELETE PRODUCT
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}