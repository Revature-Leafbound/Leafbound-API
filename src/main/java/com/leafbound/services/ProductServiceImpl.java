
package com.leafbound.services;

import java.util.List;
import java.util.UUID;

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

	public ProductServiceImpl(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Product getProductById(UUID id) {
		return productRepo.findBy(id).get();
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
