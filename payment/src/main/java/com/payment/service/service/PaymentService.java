package com.movie.database.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.database.dto.TransactionRequest;
import com.movie.database.dto.TransactionResponse;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class PaymentService {

    private String topic = "trans";
    private Properties producerProperties;
    KafkaProperties kafkaProperties;
//    Producer<String, String> producer;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransaction(TransactionRequest transactionRequest) throws JsonProcessingException {

        producerProperties.putAll(kafkaProperties.getProperties());
//        producer = new KafkaProducer<String, String>(producerProperties);
        ObjectMapper objectMapper = new ObjectMapper();
        String transcationString = objectMapper.writeValueAsString(transactionRequest);
        kafkaTemplate.send(topic, transcationString);
//        producer.send(new ProducerRecord<>(topic, transcationString));

    }
}
