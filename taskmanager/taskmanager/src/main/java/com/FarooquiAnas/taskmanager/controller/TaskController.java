package com.FarooquiAnas.taskmanager.controller;

import com.FarooquiAnas.taskmanager.dto.TaskDto;
import com.FarooquiAnas.taskmanager.service.TaskService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// ─────────────────────────────────────────────────────────────────────────────
// @RestController = @Controller + @ResponseBody
//   @Controller     → registers this as a Spring MVC controller bean
//   @ResponseBody   → every method's return value is serialized to JSON
//                     automatically (Spring uses Jackson library for this)
//
// @RequestMapping("/api/tasks") → ALL endpoints in this class are prefixed with /api/tasks
//
// FLOW OF A REQUEST:
//   HTTP Request → DispatcherServlet → TaskController → TaskService → TaskRepository → MongoDB
//   HTTP Response ←────────────────────────────────────────────────────────────────────────
//
// The DispatcherServlet is Spring's "front controller" — it routes every
// incoming request to the correct controller method.
// ─────────────────────────────────────────────────────────────────────────────
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	@Autowired
   public TaskService taskService;
//
//    public TaskController(TaskService taskService) {
//        this.taskService = taskService;
//    }

    // ──────────────────────────────────────────────────────────────────────────
    // POST /api/tasks
    // Creates a new task
    //
    // @RequestBody  → Spring deserializes the JSON body into CreateTaskRequest object
    // @Valid        → triggers validation (@NotBlank, @Size etc.) on the request object
    //                 If validation fails → MethodArgumentNotValidException is thrown
    //                 → GlobalExceptionHandler catches it → returns 400 JSON
    // ──────────────────────────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<TaskDto.TaskResponse> createTask(
            @Valid @RequestBody TaskDto.CreateTaskRequest request) {
        TaskDto.TaskResponse response = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 Created
    }

    // ──────────────────────────────────────────────────────────────────────────
    // GET /api/tasks
    // GET /api/tasks?status=PENDING
    // GET /api/tasks?priority=HIGH
    // GET /api/tasks?search=meeting
    //
    // @RequestParam(required = false) → optional query params
    // If provided, filters tasks. If not, returns all tasks.
    // ──────────────────────────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<TaskDto.TaskResponse>> getTasks(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) String search) {

        List<TaskDto.TaskResponse> tasks;

        if (status != null) {
            tasks = taskService.getTasksByStatus(status);
        } else if (priority != null) {
            tasks = taskService.getTasksByPriority(priority);
        } else if (search != null) {
            tasks = taskService.searchTasks(search);
        } else {
            tasks = taskService.getAllTasks();
        }

        return ResponseEntity.ok(tasks); // 200 OK
    }

    // ──────────────────────────────────────────────────────────────────────────
    // GET /api/tasks/{id}
    // Gets a single task by its MongoDB ObjectId
    //
    // @PathVariable → extracts {id} from the URL path
    // ──────────────────────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto.TaskResponse> getTaskById(@PathVariable String id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    // ──────────────────────────────────────────────────────────────────────────
    // PUT /api/tasks/{id}
    // Full update — replaces task fields with request body
    // ──────────────────────────────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto.TaskResponse> updateTask(
            @PathVariable String id,
            @Valid @RequestBody TaskDto.UpdateTaskRequest request) {
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    // ──────────────────────────────────────────────────────────────────────────
    // PATCH /api/tasks/{id}/complete
    // Partial update — only changes the status to COMPLETED
    //
    // PATCH vs PUT:
    //   PUT   = replace the whole resource
    //   PATCH = change one specific thing (more RESTful for status changes)
    // ──────────────────────────────────────────────────────────────────────────
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskDto.TaskResponse> markAsCompleted(@PathVariable String id) {
        return ResponseEntity.ok(taskService.markAsCompleted(id));
    }

    // ──────────────────────────────────────────────────────────────────────────
    // DELETE /api/tasks/{id}
    // Deletes a task permanently
    //
    // Returns 204 No Content (standard for successful DELETE with no response body)
    // ──────────────────────────────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // ──────────────────────────────────────────────────────────────────────────
    // GET /api/tasks/stats
    // Returns task counts — useful for frontend dashboard
    // ──────────────────────────────────────────────────────────────────────────
    @GetMapping("/stats")
    public ResponseEntity<TaskService.TaskStatsResponse> getStats() {
        return ResponseEntity.ok(taskService.getStats());
    }
}