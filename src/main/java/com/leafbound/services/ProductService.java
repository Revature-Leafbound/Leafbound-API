package com.leafbound.services;

import java.util.List;

import com.leafbound.models.Product;

public interface ProductService {

	// get all products
	List<Product> getAllProducts();

	public Product getProductById(int id);

	Product createProduct(Product product);

	Product updateProduct(Product product);

	boolean deleteProduct(Product product);

}
