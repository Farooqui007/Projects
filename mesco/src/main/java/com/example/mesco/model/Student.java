package com.example.mesco.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private LocalDate dob;

    private String gender;

    private String schemeType;

    private String status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;

    @OneToOne(mappedBy = "student")
    private BankAccount bankAccount;
}