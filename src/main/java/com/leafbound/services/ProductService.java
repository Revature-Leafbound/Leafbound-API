package com.leafbound.services;

import java.util.List;
import java.util.UUID;

import com.leafbound.models.Product;

public interface ProductService {
	
	// get all products
		List<Product> getAllProducts();

		public Product getProductById(UUID uuid);

		Product createProduct(Product product);

		Product updateProduct(Product product);

		boolean deleteProduct(Product product);
}
