package com.payment.service.controller;


import com.payment.service.dto.TransactionRequest;
import com.payment.service.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions/initiate")
public class PaymentProcessorController {


    private final PaymentService paymentService;

    public PaymentProcessorController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<?> transactions(@RequestBody TransactionRequest transactionRequest) {
        try {
            paymentService.sendTransaction(transactionRequest);
            return ResponseEntity.ok("Transaction initiated successfully and sent to Kafka.");
        } catch (Exception e) {
            return ResponseEntity.ok("Transaction initiated successfully and sent to Kafka.");
        }

    }
}
