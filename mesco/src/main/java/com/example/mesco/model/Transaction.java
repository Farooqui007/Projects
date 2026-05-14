package com.example.mesco.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionType;

    private BigDecimal amount;

    private String source;

    private String description;

    private LocalDate transactionDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;
}