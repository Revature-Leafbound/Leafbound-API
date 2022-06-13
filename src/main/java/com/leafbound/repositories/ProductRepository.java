package com.leafbound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.leafbound.models.Product;


@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer>{

	
	
	
}