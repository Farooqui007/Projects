package com.example.Task_Manager;

import org.springframework.stereotype.Component;


public enum Task {
	pending,in_progress,complete;

	public Task getDefaultTask() {
		return pending;
	}
	
}
