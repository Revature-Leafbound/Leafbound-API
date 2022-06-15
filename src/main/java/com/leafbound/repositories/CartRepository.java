package com.leafbound.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leafbound.models.Cart;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
