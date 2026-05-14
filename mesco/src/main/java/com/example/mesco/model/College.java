package com.example.mesco.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "college")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String collegeName;

    private String city;

    private String state;

    private String affiliation;

    @OneToMany(mappedBy = "college")
    private List<Student> students;
}
