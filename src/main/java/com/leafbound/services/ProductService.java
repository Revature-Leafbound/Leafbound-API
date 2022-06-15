package com.leafbound.services;

import java.util.List;

import com.leafbound.models.Product;

public interface ProductService {
	
	//get all products
	List<Product> getAllProducts();
	
	Product createProduct(Product product);
	
	Product updateProduct(Product product);
	
	boolean deleteProduct(Product product);
	
	List<Product> getAllProductsGenre(String genre);
	
}
