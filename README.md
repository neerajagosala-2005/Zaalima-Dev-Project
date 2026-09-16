# Zaalima E-Commerce Microservices Project

A scalable E-Commerce application developed using Java, Spring Boot and Microservices architecture.

## 🚀 Project Overview

This project is an E-Commerce backend built using multiple independent microservices.
The services communicate through REST APIs and Kafka-based event-driven communication.

## 🛠️ Technologies Used

- Java 17
- Spring Boot
- Spring Cloud
- Spring Cloud Eureka
- Spring Cloud Config Server
- Spring Cloud Gateway
- Apache Kafka
- PostgreSQL
- JWT Authentication
- REST APIs
- React
- HTML
- CSS
- JavaScript
- Git & GitHub

## 🏗️ Microservices

### 1. Eureka Server
Used for service discovery and registration.

### 2. Config Server
Centralized configuration management for microservices.

### 3. API Gateway
Provides a single entry point for accessing different microservices.

### 4. User Service
Handles user registration, login and JWT authentication.

### 5. Product Service
Manages product details, prices and product quantities.

### 6. Order Service
Creates and manages customer orders.

### 7. Inventory Service
Manages product inventory and updates stock when orders are created.

### 8. Payment Service
Handles payment information and payment status.

### 9. Notification Service
Handles notifications related to application events.

### 10. E-Commerce Frontend
React-based frontend for displaying products, adding products to cart and placing orders.

## 🔐 Authentication

JWT-based authentication is implemented for securing protected APIs.

## 🔄 Event-Driven Communication

Apache Kafka is used for asynchronous communication between services.

For example:

Order Created → Kafka Event → Inventory Service

## 🗄️ Database

PostgreSQL is used for storing application data.

## 🌐 API Gateway Routes

The API Gateway provides routes for:

- Product Service
- User Service
- Order Service
- Inventory Service
- Payment Service
- Notification Service

## 🖥️ Frontend Screenshot

![E-Commerce Frontend](./Screenshot%202026-09-16%20191508.png)

## 🏠 Eureka Server Dashboard

![Eureka Dashboard 1](./Screenshot%202026-09-16%20192619.png)

![Eureka Dashboard 2](./Screenshot%202026-09-16%20192724.png)

## 💻 Frontend Features

- Display products
- Add products to cart
- Remove products from cart
- Calculate cart total
- Buy Now
- Create orders through API Gateway

## ▶️ How to Run

1. Start PostgreSQL.
2. Start Kafka.
3. Start Eureka Server.
4. Start Config Server.
5. Start User Service.
6. Start Product Service.
7. Start Order Service.
8. Start Inventory Service.
9. Start Payment Service.
10. Start Notification Service.
11. Start API Gateway.
12. Start the React frontend.

### Frontend

http://localhost:5174/

### Eureka Dashboard

http://localhost:8761/

### API Gateway

http://localhost:8080/

## 📌 Project Architecture

React Frontend
        |
        v
API Gateway
        |
        +---- User Service
        +---- Product Service
        +---- Order Service
        +---- Inventory Service
        +---- Payment Service
        +---- Notification Service

Eureka Server → Service Discovery

Config Server → Centralized Configuration

Kafka → Event-Driven Communication

PostgreSQL → Database

## 👩‍💻 Developer

Neeraja Gosala

GitHub:
https://github.com/neerajagosala-2005
