package com.FarooquiAnas.taskmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

// @Document tells Spring Data this class maps to a MongoDB collection called "tasks"
// Without this, Spring doesn't know it's a DB entity
@Document(collection = "tasks")

// Lombok: auto-generates getters, setters, equals, hashCode, toString
@Data

// Lombok: generates a constructor with NO arguments → required by Spring for deserialization
@NoArgsConstructor

// Lombok: generates a constructor with ALL arguments
@AllArgsConstructor
public class Task {

    // @Id → maps to MongoDB's _id field. Spring auto-generates this as ObjectId (24-char hex string)
    @Id
    private String id;

    // @NotBlank → validation: fails if null, empty, or only whitespace
    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    // Task priority: LOW, MEDIUM, HIGH
    private Priority priority = Priority.MEDIUM;

    // Task status: PENDING or COMPLETED
    private Status status = Status.PENDING;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;

    // ─────────────────────────────────────────────
    // Enums keep status/priority type-safe
    // instead of raw Strings like "pending" vs "PENDING" vs "Pending"
    // ─────────────────────────────────────────────
    public enum Status {
        PENDING, COMPLETED
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}