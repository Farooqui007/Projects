package com.example.mesco.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "bank_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private String bankName;

    private String ifscCode;

    private String accountHolderName;

    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "bankAccount")
    private List<Transaction> transactions;
}
