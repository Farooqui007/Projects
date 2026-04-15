package com.example.SpringEcom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringEcom.model.Product;
import com.example.SpringEcom.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;

	public List<Product> getAllProducts() {
		
		return productRepo.findAll();
	}
	
}
