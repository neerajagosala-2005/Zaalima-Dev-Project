package com.ecommerce.api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.stripPrefix;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class RouteConfig {

    // Product Service
    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {

        System.out.println("PRODUCT ROUTE LOADED");

        return route("product-service")
                .GET("/product-service/api/products", http())
                .POST("/product-service/api/products", http())
                .PUT("/product-service/api/products/**", http())
                .DELETE("/product-service/api/products/**", http())
                .before(uri("http://localhost:8083"))
                .before(stripPrefix(1))
                .build();
    }

    // User Service
    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {

        System.out.println("USER ROUTE LOADED");

        return route("user-service")
                .GET("/user-service/**", http())
                .POST("/user-service/**", http())
                .PUT("/user-service/**", http())
                .DELETE("/user-service/**", http())
                .before(uri("http://localhost:8081"))
                .before(stripPrefix(1))
                .build();
    }

    // Order Service
    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {

        System.out.println("ORDER ROUTE LOADED");

        return route("order-service")
                .GET("/order-service/**", http())
                .POST("/order-service/**", http())
                .PUT("/order-service/**", http())
                .DELETE("/order-service/**", http())
                .before(uri("http://localhost:8082"))
                .before(stripPrefix(1))
                .build();
    }

    // Inventory Service
    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {

        System.out.println("INVENTORY ROUTE LOADED");

        return route("inventory-service")
                .GET("/inventory-service/**", http())
                .POST("/inventory-service/**", http())
                .PUT("/inventory-service/**", http())
                .DELETE("/inventory-service/**", http())
                .before(uri("http://localhost:8084"))
                .before(stripPrefix(1))
                .build();
    }

    // Payment Service
    @Bean
    public RouterFunction<ServerResponse> paymentServiceRoute() {

        System.out.println("PAYMENT ROUTE LOADED");

        return route("payment-service")
                .GET("/payment-service/**", http())
                .POST("/payment-service/**", http())
                .PUT("/payment-service/**", http())
                .DELETE("/payment-service/**", http())
                .before(uri("http://localhost:8085"))
                .before(stripPrefix(1))
                .build();
    }

    // Notification Service
    @Bean
    public RouterFunction<ServerResponse> notificationServiceRoute() {

        System.out.println("NOTIFICATION ROUTE LOADED");

        return route("notification-service")
                .GET("/notification-service/**", http())
                .POST("/notification-service/**", http())
                .PUT("/notification-service/**", http())
                .DELETE("/notification-service/**", http())
                .before(uri("http://localhost:8086"))
                .before(stripPrefix(1))
                .build();
    }
}