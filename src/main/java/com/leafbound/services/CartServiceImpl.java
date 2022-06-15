package com.leafbound.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.leafbound.models.Carts;
import com.leafbound.repositories.CartRepository;

public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository crepo;

	@Override
	public Carts findById(int id) {
		return crepo.findById(id);
	}

	@Override
	public boolean addtoCart(Carts cart) {
		int pk = crepo.save(cart).getId();
		return (pk > 0) ? true : false;
	}


	@Override
	public boolean updateCart(Carts cart) {
		Carts target = crepo.findById(cart.getById());

		target.setProductId(cart.getProductId());
		target.setQuantity(cart.getQuantity());
		target.setCustomerId(cart.getCustomerId());

		return (crepo.save(target) != null) ? true : false;
	}

//	@Override
//	public boolean updateCart(Carts cart) {
//		return crepo.save(cart) != null;
//	}


	@Override
	public Carts deleteCart(int id) {
		return crepo.delete(id);
	}

}
