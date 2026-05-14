package com.example.Task_Manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Task_Manager.Task;
import com.example.Task_Manager.dto.TaskCreateDTO;
import com.example.Task_Manager.dto.TaskResponseDTO;
import com.example.Task_Manager.model.TaskModel;
import com.example.Task_Manager.repo.TaskRepo;

@Service
public class TaskService {
	@Autowired
	TaskRepo repo;
	
	public TaskResponseDTO createTask(TaskCreateDTO dto) {

	    TaskModel task = new TaskModel();

	    task.setTitle(dto.getTitle());
	    task.setDescription(dto.getDescription());
	    task.setTaskStatus(Task.valueOf(dto.getTaskStatus()));
	    task.setDueDate(dto.getDueDate());

	    TaskModel saved = repo.save(task);

	    return mapToDTO(saved);
	}

	private TaskResponseDTO mapToDTO(TaskModel task) {
	    TaskResponseDTO dto = new TaskResponseDTO();

	    dto.setTaskId(task.getTaskId());
	    dto.setTitle(task.getTitle());
	    dto.setDescription(task.getDescription());
	    dto.setTaskStatus(task.getTaskStatus().name());
	    dto.setDueDate(task.getDueDate());

	    return dto;
	}

	public List<TaskModel> getAllTasks() {
		return repo.findAll();
	}

	public TaskModel getTask(int id) {
		return repo.findById(id).get();
		
	}

	public TaskModel addTask(TaskModel task) {
		return repo.save(task);
		
	}

	public void delete(int id) {
		repo.deleteById(id);
	}

	public void updateTask(TaskModel model) {
		repo.save(model);
		
	}

}
