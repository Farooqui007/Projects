package com.example.JobApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.JobApp.model.JobPost;
import com.example.JobApp.service.JobService;

import org.springframework.ui.Model;

@Controller
public class JobController {
	
	@Autowired
	private JobService service;
	@GetMapping({"/","home"})
	public String home() {
		return"home";
	}
	
	@GetMapping("addjob")
	public String addJob() {
		return "addjob";
	}
	@PostMapping("handleForm")
	public String handleForm(JobPost jobPost) {
		service.add(jobPost);
		return "success";
	}
	@GetMapping("viewalljobs")
	public String viewJobs(Model m) {
		List<JobPost> job = service.getAllJobs();
		m.addAttribute("jobPosts",job);
		return "viewalljobs";
	}
	
}
