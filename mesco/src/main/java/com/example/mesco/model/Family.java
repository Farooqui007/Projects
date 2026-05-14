package com.example.mesco.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "family")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String familyName;

    private String address;

    private BigDecimal annualIncome;

    private Integer membersCount;

    @OneToMany(mappedBy = "family")
    private List<Student> students;
}
