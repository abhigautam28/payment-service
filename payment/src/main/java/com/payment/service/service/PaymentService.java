package com.payment.service.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.payment.service.dto.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Slf4j
@Service
public class PaymentService {

    private String topic = "transactions";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransaction(TransactionRequest transactionRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String transcationString = objectMapper.writeValueAsString(transactionRequest);
        System.out.println(topic);
        kafkaTemplate.send(topic, transcationString);


    }
}
