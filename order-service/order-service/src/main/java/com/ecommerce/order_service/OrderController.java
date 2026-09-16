package com.ecommerce.order_service;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.ecommerce.order_service.service.KafkaProducerService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;

    public OrderController(OrderRepository orderRepository,
                           KafkaProducerService kafkaProducerService) {
        this.orderRepository = orderRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {

        Order savedOrder = orderRepository.save(order);

        kafkaProducerService.sendOrderCreatedEvent(
                savedOrder.getId().toString(),
                savedOrder.getProductName(),
                savedOrder.getQuantity()
        );

        return savedOrder;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "Order deleted successfully";
    }
}