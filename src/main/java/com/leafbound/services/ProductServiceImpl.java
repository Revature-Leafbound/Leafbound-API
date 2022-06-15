
package com.leafbound.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leafbound.models.Product;
import com.leafbound.repositories.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Product getProductById(int id) {
		return productRepo.findById(id);
	}

	@Override
	public Product updateProduct(Product product) {
		Product target = productRepo.findById(product.getId());
		product.setId(target.getId());
		return productRepo.save(product);

	}

	@Override
	public boolean deleteProduct(Product product) {
		productRepo.delete(product);
		return true;
	}

}
