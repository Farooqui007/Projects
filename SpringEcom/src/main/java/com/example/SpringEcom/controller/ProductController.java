package com.example.SpringEcom.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringEcom.model.Product;
import com.example.SpringEcom.service.ProductService;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getProduct() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id){
		Product product = productService.getProductByid(id);
		
		if(product != null) {
			return new ResponseEntity<>(product , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
		Product saveProduct;
		try {
			saveProduct = productService.addProduct(product,imageFile);
			return new ResponseEntity<>(saveProduct,HttpStatus.CREATED);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
