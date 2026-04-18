package com.example.Task_Manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Task_Manager.model.TaskModel;

@Repository
public interface TaskRepo extends JpaRepository<TaskModel, Integer> {

}
