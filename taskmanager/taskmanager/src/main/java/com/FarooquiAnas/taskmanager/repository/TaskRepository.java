package com.FarooquiAnas.taskmanager.repository;

import com.FarooquiAnas.taskmanager.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// ─────────────────────────────────────────────────────────────────────────────
// HOW SPRING DATA REPOSITORY WORKS:
//
// You define an INTERFACE — no implementation class needed.
// Spring reads method names at startup and auto-generates MongoDB queries.
//
// MongoRepository<Task, String>:
//   Task   → the document type
//   String → the type of the @Id field
//
// You get these for FREE (no code needed):
//   save(task)            → insert or update
//   findById(id)          → returns Optional<Task>
//   findAll()             → returns all tasks
//   deleteById(id)        → deletes by id
//   count()               → total count
//   existsById(id)        → returns boolean
// ─────────────────────────────────────────────────────────────────────────────
@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    // Spring reads "findByStatus" and generates: db.tasks.find({ status: ? })
    List<Task> findByStatus(Task.Status status);

    // Generates: db.tasks.find({ priority: ? })
    List<Task> findByPriority(Task.Priority priority);

    // Generates a case-insensitive regex search on the title field
    List<Task> findByTitleContainingIgnoreCase(String keyword);

    // Combined filter: status + priority
    List<Task> findByStatusAndPriority(Task.Status status, Task.Priority priority);

    // Custom query using MongoDB JSON syntax — for when method names get too long
    @Query("{ 'status': ?0, 'priority': ?1 }")
    List<Task> findTasksByStatusAndPriority(String status, String priority);

    // Count pending tasks
    long countByStatus(Task.Status status);
}