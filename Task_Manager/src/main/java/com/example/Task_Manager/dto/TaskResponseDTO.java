package com.example.Task_Manager.dto;

import java.sql.Date;

import com.example.Task_Manager.Task;
import com.example.Task_Manager.model.TaskModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private Integer taskId;
    private String title;
    private String description;
    private String taskStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dueDate;
}
