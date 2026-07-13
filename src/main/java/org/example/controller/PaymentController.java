package org.example.controller;


import org.example.entity.Payment;
import org.example.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {


    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {

        this.paymentService = paymentService;

    }



    // Create Payment
    @PostMapping
    public ResponseEntity<Payment> createPayment(
            @RequestBody Payment payment
    ){

        return ResponseEntity.ok(
                paymentService.createPayment(payment)
        );

    }



    // Get All Payments
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments(){

        return ResponseEntity.ok(
                paymentService.getAllPayments()
        );

    }



    // Get Payment By ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                paymentService.getPaymentById(id)
        );

    }



    // Delete Payment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(
            @PathVariable Long id
    ){

        paymentService.deletePayment(id);

        return ResponseEntity.ok(
                "Payment deleted successfully"
        );

    }

}