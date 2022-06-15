package com.leafbound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.leafbound.models.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

	// get all product info from id
	@Query(value = "SELECT * FROM products where id=?1", nativeQuery = true)
	public Product findById(int id);

}