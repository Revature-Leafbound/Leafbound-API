package com.leafbound.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbound.models.Cart;
import com.leafbound.repositories.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	private Logger logger = Logger.getLogger(CartServiceImpl.class);

	@Autowired
	private CartRepository repository;

	@Override
	public boolean addtoCart(Cart cart) {
		int pk = repository.save(cart).getId();
		return (pk > 0);
	}

	@Override
	public boolean deleteCart(int id) {
		try {
			repository.deleteById(id);
		} catch (IllegalArgumentException e) {
			logger.warn("Unable to delete user: " + e.getMessage());
			return false;
		}
		return true;
	}

}
