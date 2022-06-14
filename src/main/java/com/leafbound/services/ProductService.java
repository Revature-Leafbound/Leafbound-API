package com.leafbound.services;

import java.util.List;

import com.leafbound.models.Product;

public interface ProductService {
	
	//get all products
	List<Product> getAllProducts();
	
	boolean createProduct(Product product);
}
