package com.example.Test1.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class TestController {

	@GetMapping("/data")
	public String getData() {
		return "Hello world form BackEnd";
	}
}
