package com.example.Task_Manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Task_Manager.dto.TaskCreateDTO;
import com.example.Task_Manager.dto.TaskResponseDTO;
import com.example.Task_Manager.model.TaskModel;
import com.example.Task_Manager.service.TaskService;

@RestController

public class TaskController {

	@Autowired
	TaskService service;

//	@GetMapping("tasks")
//	public List<TaskModel> allTasks() {
//		return service.getAllTasks();
//	}

	@GetMapping("task/{taskId}")
	public TaskModel task(@PathVariable("taskId") int id) {
		return service.getTask(id);
	}

	@PostMapping("task")
	public TaskResponseDTO addTask(@RequestBody TaskCreateDTO dto) {
		return service.createTask(dto);
	}
	
	@DeleteMapping("task/{id}")
	public String delete(@PathVariable int id) {
		service.delete(id);
		return "deleted";
	}
	
	@PutMapping("task")
	public TaskModel updateTask(@RequestBody TaskModel model) {
		service.updateTask(model);
		return service.getTask(model.getTaskId());
	}
}
