package com.example.calculator_backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.calculator_backend.model.Calculation;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
	List<Calculation> findTop10ByOrderByIdDesc();
}
