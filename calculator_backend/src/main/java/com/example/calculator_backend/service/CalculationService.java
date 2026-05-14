package com.example.calculator_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.calculator_backend.CalculatorBackendApplication;
import com.example.calculator_backend.model.Calculation;
import com.example.calculator_backend.repo.CalculationRepository;

@Service
public class CalculationService {

    private final CalculatorBackendApplication calculatorBackendApplication;
	
	@Autowired
	private CalculationRepository repo;

    CalculationService(CalculatorBackendApplication calculatorBackendApplication) {
        this.calculatorBackendApplication = calculatorBackendApplication;
    }

	public Calculation add(double num1, double num2) {

        double result = num1 + num2;

        Calculation calculation = new Calculation();

        calculation.setNum1(num1);
        calculation.setNum2(num2);
        calculation.setOperation("ADD");
        calculation.setResult(result);
        calculation.setCreatedAt(LocalDateTime.now());

        return repo.save(calculation);
    }
	
	public Calculation subtract(Double num1, Double num2) {

	    double result = num1 - num2;

	    Calculation calculation = new Calculation();

	    calculation.setNum1(num1);
	    calculation.setNum2(num2);
	    calculation.setOperation("SUBTRACT");
	    calculation.setResult(result);
	    calculation.setCreatedAt(LocalDateTime.now());

	    return repo.save(calculation);
	}
	
	public Calculation divide(Double num1, Double num2) {
		
		if(num2 == 0) {
	        throw new RuntimeException("Division by zero is not allowed");
	    }

	    double result = num1 / num2;

	    Calculation calculation = new Calculation();

	    calculation.setNum1(num1);
	    calculation.setNum2(num2);
	    calculation.setOperation("DIVIDE");
	    calculation.setResult(result);
	    calculation.setCreatedAt(LocalDateTime.now());

	    return repo.save(calculation);
	}
	
	public Calculation multiply(Double num1, Double num2) {

	    double result = num1 * num2;

	    Calculation calculation = new Calculation();

	    calculation.setNum1(num1);
	    calculation.setNum2(num2);
	    calculation.setOperation("MULTIPLY");
	    calculation.setResult(result);
	    calculation.setCreatedAt(LocalDateTime.now());

	    return repo.save(calculation);
	}

	public List<Calculation> getHistory() {
		return repo.findTop10ByOrderByIdDesc();
	}
}
