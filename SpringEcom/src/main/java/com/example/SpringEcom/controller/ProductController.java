package com.example.SpringEcom.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.SpringEcom.model.Product;
import com.example.SpringEcom.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/product")
	public List<Product> getProduct() {
		return productService.getAllProducts();
	}
}
