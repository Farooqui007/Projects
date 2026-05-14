package com.example.calculator_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.calculator_backend.model.Calculation;
import com.example.calculator_backend.service.CalculationService;

@RestController
@RequestMapping("/calculate")
@CrossOrigin(origins = "http://localhost:4200")
public class CalculationController {
	
	@Autowired
    private CalculationService service;
	
	@PostMapping("/add")
    public Calculation add(
            @RequestParam Double num1,
            @RequestParam Double num2) {

        return service.add(num1, num2);
    }
	
	@GetMapping("/history")
	public List<Calculation> getHistory() {

	    return service.getHistory();

	}
	
	@PostMapping("/subtract")
	public Calculation subtract(
	        @RequestParam Double num1,
	        @RequestParam Double num2) {

	    return service.subtract(num1, num2);
	}
	
	@PostMapping("/multiply")
	public Calculation multiply(
	        @RequestParam Double num1,
	        @RequestParam Double num2) {

	    return service.multiply(num1, num2);
	}
	
	@PostMapping("/divide")
	public Calculation divide(
	        @RequestParam Double num1,
	        @RequestParam Double num2) {

	    return service.divide(num1, num2);
	}
}
