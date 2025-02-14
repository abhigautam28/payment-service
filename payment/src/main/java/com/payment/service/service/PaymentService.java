package com.payment.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.service.dto.Transaction;
import com.payment.service.dto.TransactionRequest;
import com.payment.service.exception.CustomException;
import com.payment.service.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PaymentService {

    private String topic = "transactions";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final TransactionRepository transactionRepository;

    public PaymentService(KafkaTemplate<String, String> kafkaTemplate, TransactionRepository transactionRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.transactionRepository = transactionRepository;

    }

    public  ResponseEntity<?> sendTransaction(TransactionRequest transactionRequest) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String transcationString = objectMapper.writeValueAsString(transactionRequest);
            kafkaTemplate.send(topic, transcationString);

            //to track transaction status
            Optional<Transaction> transaction = transactionRepository.findById(transactionRequest.getTransactionId());
            if (transaction.isPresent()) {
                Transaction transactionDto = transaction.get();
                if (transactionDto.getStatus().equalsIgnoreCase("completed")) {
                    return ResponseEntity.status(HttpStatus.OK).body("Transaction successful");
                }
            }
            throw new CustomException(HttpStatus.BAD_REQUEST, "Transaction failed");
          } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Transaction failed");
         }

      }
}
