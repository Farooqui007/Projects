package com.FarooquiAnas.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// ─────────────────────────────────────────────────────────────────────────────
// @RestControllerAdvice = a special Spring bean that intercepts exceptions
// thrown from ANY controller in the app.
//
// Without this: Spring returns ugly HTML error pages or raw stack traces.
// With this:    We return clean, structured JSON error responses.
//
// This is the "global try-catch" for your entire API.
// ─────────────────────────────────────────────────────────────────────────────
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ── Handles TaskNotFoundException → 404 Not Found ────────────────────────
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFound(TaskNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Task Not Found",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // ── Handles @Valid failures → 400 Bad Request ─────────────────────────────
    // Triggered when @NotBlank, @Size etc. fail on incoming request body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();

        // Extract each field's error message
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            fieldErrors.put(fieldName, message);
        });

        ValidationErrorResponse response = new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                fieldErrors,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // ── Catch-all for unexpected errors → 500 Internal Server Error ───────────
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Something went wrong. Please try again.",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Inner classes for error response shapes
    // (kept here for simplicity, can be moved to separate files)
    // ─────────────────────────────────────────────────────────────────────────

    public record ErrorResponse(
            int status,
            String error,
            String message,
            LocalDateTime timestamp
    ) {}

    public record ValidationErrorResponse(
            int status,
            String error,
            Map<String, String> fieldErrors,
            LocalDateTime timestamp
    ) {}
}