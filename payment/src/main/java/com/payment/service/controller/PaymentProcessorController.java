package com.payment.service.controller;


import com.payment.service.dto.TransactionRequest;
import com.payment.service.dto.TransactionResponse;
import com.payment.service.exception.CustomException;
import com.payment.service.exception.ErrorResponse;
import com.payment.service.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

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
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid amount!");
            throw new CustomException(HttpStatus.BAD_REQUEST, "Invalid amount");
        }

        return paymentService.sendTransaction(transactionRequest);

    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handledCustomException(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getHttpStatus().value(), e.getMsg());
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }
}
