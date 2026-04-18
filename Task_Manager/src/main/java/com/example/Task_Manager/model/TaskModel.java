package com.example.Task_Manager.model;

import java.sql.Date;


import com.example.Task_Manager.Task;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TaskModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;
	private String title;
	private String description;
	@Enumerated(EnumType.STRING)
	private Task taskStatus;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dueDate;
	
	
	
	
}
