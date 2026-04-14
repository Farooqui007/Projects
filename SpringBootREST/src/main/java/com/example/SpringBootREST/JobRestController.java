package com.example.SpringBootREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootREST.model.JobPost;
import com.example.SpringBootREST.service.JobService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobRestController {
	
	@Autowired
	private JobService service;
	
	@GetMapping("jobPosts")
	public List<JobPost> getAllJobs() {
		return service.getAllJobs();
	}
	@GetMapping("jobPost/{PostId}")
	public JobPost getJob(@PathVariable("PostId") int PostId) {
		return service.getJob(PostId);
	}
	@PostMapping("jobPost")
	public JobPost addJob(@RequestBody JobPost jobPost) {
		service.add(jobPost);
		System.out.println("ADDED");
		return service.getJob(jobPost.getPostId());
	}
	@PutMapping("jobPost")
	public JobPost updateJob(@RequestBody JobPost jobPost) {
		service.updateJob(jobPost);
		return service.getJob(jobPost.getPostId());
	}
	@DeleteMapping("jobPost/{postId}")
	public String delete(@PathVariable("postId") int id) {
		service.delete(id);
		return "Done";	}
	
	@GetMapping("load")
	public String loadData() {
		service.loadData();
		return "success";
	}
	@GetMapping("jobPosts/keyword/{keyword}")
	public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
		return service.search(keyword);
	}
}
