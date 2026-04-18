package com.example.Task_Manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Task_Manager.controller.TaskController;
import com.example.Task_Manager.model.TaskModel;
import com.example.Task_Manager.repo.TaskRepo;

@Service
public class TaskService {
	@Autowired
	TaskRepo repo;

	public List<TaskModel> getAllTasks() {
		return repo.findAll();
	}

	public TaskModel getTask(int id) {
		return repo.findById(id).get();
		
	}

	public TaskModel addTask(TaskModel task) {
		return repo.save(task);
		
	}

}
