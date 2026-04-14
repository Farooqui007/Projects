package com.example.JobApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JobApp.model.JobPost;
import com.example.JobApp.repo.JobRepo;

@Service
public class JobService {
	@Autowired
	private JobRepo repo;
	public void add(JobPost post) {
		repo.addJob(post);
	}
	public List<JobPost> getAllJobs(){
		return repo.addAllJobs();
	}
}
