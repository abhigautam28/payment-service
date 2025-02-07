package com.movie.database.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TransactionRequest {

 @NotNull
 private UUID transactionId;

 @NotNull
 @DecimalMin(value = "0.01")
 private BigDecimal amount;
 @NotNull
 private String currency;
 @NotNull
 private String senderId;
 @NotNull
 private String receiverId;
 @NotNull
 private String transactionType;
}
