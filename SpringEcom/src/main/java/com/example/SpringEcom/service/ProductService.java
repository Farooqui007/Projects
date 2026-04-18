package com.example.SpringEcom.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringEcom.model.Product;
import com.example.SpringEcom.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;

	public List<Product> getAllProducts() {
		
		return productRepo.findAll();
	}

	public Product getProductByid(int id) {
		return productRepo.findById(id).get();
	}

	public Product addProduct(Product product, MultipartFile image) throws IOException {
		// TODO Auto-generated method stub
		product.setImageName(image.getOriginalFilename());
		product.setImageType(image.getContentType());
		product.setImageData(image.getBytes());
		return productRepo.save(null);
	}
	
}
