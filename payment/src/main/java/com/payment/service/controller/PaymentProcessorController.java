package com.payment.service.controller;


import com.payment.service.dto.TransactionRequest;
import com.payment.service.dto.TransactionResponse;
import com.payment.service.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transactions/initiate")
public class PaymentProcessorController {


    private final PaymentService paymentService;

    public PaymentProcessorController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<?> transactions(@RequestBody TransactionRequest transactionRequest) {
        if (transactionRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid amount!");
        }

        return paymentService.sendTransaction(transactionRequest);


    }
}
