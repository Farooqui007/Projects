package com.FarooquiAnas.taskmanager.service;

import com.FarooquiAnas.taskmanager.dto.TaskDto;
import com.FarooquiAnas.taskmanager.exception.TaskNotFoundException;
import com.FarooquiAnas.taskmanager.model.Task;
import com.FarooquiAnas.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

// ─────────────────────────────────────────────────────────────────────────────
// @Service → registers this class as a Spring Bean of type "service"
//
// Spring creates ONE instance (singleton) of this class and keeps it in the
// "Application Context" (Spring's container of all managed objects).
//
// Any class that needs TaskService just declares it as a constructor parameter
// and Spring automatically injects the same singleton instance.
//
// This is Dependency Injection (DI) — one of Spring's core features.
// ─────────────────────────────────────────────────────────────────────────────
@Service
public class TaskService {

    // Final = cannot be reassigned after construction (good practice)
    private final TaskRepository taskRepository;

    // ── Constructor Injection ──────────────────────────────────────────────────
    // Spring sees this constructor, finds a TaskRepository bean in its container,
    // and passes it in automatically. No @Autowired needed with single constructor.
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // ── CREATE ─────────────────────────────────────────────────────────────────
    public TaskDto.TaskResponse createTask(TaskDto.CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());

        // Set priority if provided, else default to MEDIUM
        task.setPriority(request.getPriority() != null ? request.getPriority() : Task.Priority.MEDIUM);

        // These should always be set by server, never by client
        task.setStatus(Task.Status.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        Task saved = taskRepository.save(task);  // save() → INSERT if new, UPDATE if id exists
        return TaskDto.TaskResponse.from(saved);
    }

    // ── READ ALL ───────────────────────────────────────────────────────────────
    public List<TaskDto.TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskDto.TaskResponse::from)  // convert each Task → TaskResponse
                .toList();
    }

    // ── READ BY ID ─────────────────────────────────────────────────────────────
    public TaskDto.TaskResponse getTaskById(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        // orElseThrow → if Optional is empty (task not found), throw our custom exception
        // GlobalExceptionHandler catches it and returns 404 JSON automatically
        return TaskDto.TaskResponse.from(task);
    }

    // ── READ BY STATUS ─────────────────────────────────────────────────────────
    public List<TaskDto.TaskResponse> getTasksByStatus(String statusStr) {
        Task.Status status = Task.Status.valueOf(statusStr.toUpperCase());
        return taskRepository.findByStatus(status)
                .stream()
                .map(TaskDto.TaskResponse::from)
                .toList();
    }

    // ── READ BY PRIORITY ───────────────────────────────────────────────────────
    public List<TaskDto.TaskResponse> getTasksByPriority(String priorityStr) {
        Task.Priority priority = Task.Priority.valueOf(priorityStr.toUpperCase());
        return taskRepository.findByPriority(priority)
                .stream()
                .map(TaskDto.TaskResponse::from)
                .toList();
    }

    // ── SEARCH BY KEYWORD ──────────────────────────────────────────────────────
    public List<TaskDto.TaskResponse> searchTasks(String keyword) {
        return taskRepository.findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(TaskDto.TaskResponse::from)
                .toList();
    }

    // ── UPDATE ─────────────────────────────────────────────────────────────────
    public TaskDto.TaskResponse updateTask(String id, TaskDto.UpdateTaskRequest request) {
        // First verify the task exists — throws 404 if not found
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        // Only update fields that client is allowed to change
        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setDueDate(request.getDueDate());
        if (request.getPriority() != null) {
            existing.setPriority(request.getPriority());
        }
        existing.setUpdatedAt(LocalDateTime.now()); // always track when it was last modified

        return TaskDto.TaskResponse.from(taskRepository.save(existing));
    }

    // ── MARK AS COMPLETED ──────────────────────────────────────────────────────
    public TaskDto.TaskResponse markAsCompleted(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(Task.Status.COMPLETED);
        task.setUpdatedAt(LocalDateTime.now());
        return TaskDto.TaskResponse.from(taskRepository.save(task));
    }

    // ── DELETE ─────────────────────────────────────────────────────────────────
    public void deleteTask(String id) {
        // Check task exists before trying to delete (throws 404 if not found)
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    // ── STATS ──────────────────────────────────────────────────────────────────
    public TaskStatsResponse getStats() {
        long total = taskRepository.count();
        long pending = taskRepository.countByStatus(Task.Status.PENDING);
        long completed = taskRepository.countByStatus(Task.Status.COMPLETED);
        return new TaskStatsResponse(total, pending, completed);
    }

    // Simple record to hold stats (Java 17 records = immutable data holders)
    public record TaskStatsResponse(long total, long pending, long completed) {}
}