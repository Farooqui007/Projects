package com.FarooquiAnas.taskmanager.exception;

// ─────────────────────────────────────────────────────────────────────────────
// WHY CUSTOM EXCEPTIONS?
//
// Instead of throwing generic RuntimeException("Task not found"),
// we throw a specific TaskNotFoundException.
//
// This lets the GlobalExceptionHandler catch it by type and return
// the correct HTTP status (404 Not Found) automatically.
// ─────────────────────────────────────────────────────────────────────────────

public class TaskNotFoundException extends RuntimeException {

    private final String taskId;

    public TaskNotFoundException(String taskId) {
        super("Task not found with id: " + taskId);
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }
}