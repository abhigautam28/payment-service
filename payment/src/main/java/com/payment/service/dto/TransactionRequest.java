package com.payment.service.dto;

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

 public UUID getTransactionId() {
  return transactionId;
 }

 public void setTransactionId(UUID transactionId) {
  this.transactionId = transactionId;
 }

 public BigDecimal getAmount() {
  return amount;
 }

 public void setAmount(BigDecimal amount) {
  this.amount = amount;
 }

 public String getCurrency() {
  return currency;
 }

 public void setCurrency(String currency) {
  this.currency = currency;
 }

 public String getSenderId() {
  return senderId;
 }

 public void setSenderId(String senderId) {
  this.senderId = senderId;
 }

 public String getReceiverId() {
  return receiverId;
 }

 public void setReceiverId(String receiverId) {
  this.receiverId = receiverId;
 }

 public String getTransactionType() {
  return transactionType;
 }

 public void setTransactionType(String transactionType) {
  this.transactionType = transactionType;
 }

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
