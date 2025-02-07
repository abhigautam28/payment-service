package com.payment.service.dto;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TransactionResponse {
    private String response;


    public void setResponse(String response) {
        this.response = response;
    }
}
