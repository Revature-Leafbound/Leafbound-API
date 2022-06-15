package com.leafbound.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.leafbound.models.Carts;
import com.leafbound.repositories.CartRepository;

public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository crepo;

	@Override
	public boolean addtoCart(Carts cart) {
		int pk = crepo.save(cart).getId();
		return (pk > 0);
	}

	@Override
	public Carts deleteCart(int id) {
		return crepo.delete(id);
	}

}
