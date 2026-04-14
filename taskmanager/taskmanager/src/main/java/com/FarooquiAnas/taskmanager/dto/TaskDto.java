package com.FarooquiAnas.taskmanager.dto;

import com.FarooquiAnas.taskmanager.model.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

// ─────────────────────────────────────────────────────────────────────────────
// WHY DTOs?
//
// You should NEVER expose your model (Task.java) directly to the outside world.
// Reason: your model might have sensitive fields, internal fields (like createdAt),
// or fields that shouldn't be set by the client (like id, status).
//
// DTO (Data Transfer Object) = the shape of data that crosses the API boundary.
//
// CreateTaskRequest  → what the CLIENT sends when creating a task
// UpdateTaskRequest  → what the CLIENT sends when updating a task
// TaskResponse       → what the SERVER sends back to the client
// ─────────────────────────────────────────────────────────────────────────────

public class TaskDto {

    // ── What the client sends when CREATING a task ──────────────────────────
    @Data
    public static class CreateTaskRequest {

        @NotBlank(message = "Title is required")
        @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
        private String title;

        @Size(max = 500, message = "Description cannot exceed 500 characters")
        private String description;

        private Task.Priority priority; // optional, defaults to MEDIUM in model
        private LocalDateTime dueDate;  // optional
    }

    // ── What the client sends when UPDATING a task ───────────────────────────
    @Data
    public static class UpdateTaskRequest {

        @NotBlank(message = "Title is required")
        @Size(min = 2, max = 100)
        private String title;

        @Size(max = 500)
        private String description;

        private Task.Priority priority;
        private LocalDateTime dueDate;
    }

    // ── What the server sends BACK to the client ─────────────────────────────
    // Notice: we expose id, status, timestamps — things the client needs to see
    @Data
    public static class TaskResponse {
        private String id;
        private String title;
        private String description;
        private Task.Priority priority;
        private Task.Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime dueDate;

        // Static factory method: converts a Task model → TaskResponse DTO
        // This keeps mapping logic in one place
        public static TaskResponse from(Task task) {
            TaskResponse response = new TaskResponse();
            response.setId(task.getId());
            response.setTitle(task.getTitle());
            response.setDescription(task.getDescription());
            response.setPriority(task.getPriority());
            response.setStatus(task.getStatus());
            response.setCreatedAt(task.getCreatedAt());
            response.setUpdatedAt(task.getUpdatedAt());
            response.setDueDate(task.getDueDate());
            return response;
        }
    }
}