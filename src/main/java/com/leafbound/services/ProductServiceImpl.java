
package com.leafbound.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leafbound.models.Product;
import com.leafbound.repositories.ProductRepository;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public boolean createProduct(Product product) {
		
		int pk = productRepo.save(product).getId();
		return (pk > 0) ? true : false;
	}

}