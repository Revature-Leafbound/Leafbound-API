package com.leafbound.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.leafbound.models.Carts;
import com.leafbound.repositories.CartRepository;

public class CartServiceImpl implements CartService {
	
	
	
	@Autowired
	private CartRepository crepo;

	@Override
	public Products findProdById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addtoCart(Carts cart) {
		return crepo.save(cart) != null;
	}

	@Override
	public boolean updateCart(Carts cart) {
		return crepo.save(cart) != null;
	}

	@Override
	public Products deleteCart(int id) {
		crepo.delete(product);
		return true;
	}

}
