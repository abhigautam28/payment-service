package com.payment.service.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.payment.service.dto.Transaction;
import com.payment.service.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class TransactionConsumer {

    private final TransactionRepository transactionRepository;
    private final ObjectMapper objectMapper;

    public TransactionConsumer(TransactionRepository transactionRepository, ObjectMapper objectMapper) {
        this.transactionRepository = transactionRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "transactions", groupId = "transaction-group")
    @Transactional
    public void processTransaction(String message) {
        try {

            Transaction transaction = objectMapper.readValue(message, Transaction.class);


            boolean isValid = verifyTransaction(transaction);
            if (isValid) {
                transaction.setStatus("completed");
            } else {
                transaction.setStatus("failed");
            }
            transactionRepository.save(transaction);

        } catch (Exception  e) {
            System.err.println("Error processing transaction: " + e.getMessage());

        }
    }

    //to make sure we are sending valid amount
    private boolean verifyTransaction(Transaction transaction) {
        return transaction.getAmount().compareTo(new BigDecimal("1000")) <= 0;
    }
}
