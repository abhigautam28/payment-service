package com.movie.database.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.database.dto.Transaction;
import com.movie.database.repository.TransactionRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
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
            // Convert JSON message to Transaction object
            Transaction transaction = objectMapper.readValue(message, Transaction.class);

            // Perform transaction verification
            boolean isValid = verifyTransaction(transaction);
            if (isValid) {
                transaction.setStatus("COMPLETED");
            } else {
                transaction.setStatus("FAILED");
            }

            // Save transaction to database
            transactionRepository.save(transaction);
//            System.out.println("Transaction processed successfully: " + transaction.get);

        } catch (Exception  e) {
            System.err.println("Error processing transaction: " + e.getMessage());
            // Implement retry mechanism (if needed)
        }
    }

    private boolean verifyTransaction(Transaction transaction) {
        // Simulating balance check for sender
        return transaction.getAmount().compareTo(new BigDecimal("1000")) <= 0; // Allow transactions up to 1000
    }
}
