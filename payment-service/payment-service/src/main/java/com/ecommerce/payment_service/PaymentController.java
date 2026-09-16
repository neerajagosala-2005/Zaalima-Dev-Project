package com.ecommerce.payment_service;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Create Payment
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        payment.setStatus("SUCCESS");
        return paymentRepository.save(payment);
    }

    // Get All Payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Get Payment by ID
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    // Delete Payment
    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Long id) {

        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return "Payment deleted successfully";
        }

        return "Payment not found";
    }
}