package com.movie.database.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    private UUID transactionId;

    private BigDecimal amount;
    private String currency;
    private String senderId;
    private String receiverId;
    private String transactionType;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
