package com.leafbound.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbound.models.Carts;
import com.leafbound.repository.CartsRepository;

@Service
public class CartsServiceImpl implements CartsService {
	@Autowired
	private CartsRepository crepo;

	@Override
	public boolean updateCart(Carts cart) {

		return crepo.save(cart) != null;
	}

	@Override
	public boolean deleteCart(Carts cart) {
		crepo.delete(cart);
		return true;
	}

	@Override
	public Carts findById(int id) {
		
		Optional<Carts> cart =crepo.findById(id);
		return cart.isPresent() ? cart.get() : null;
	}

	@Override
	public boolean addtoCart(Carts cart) {
		return crepo.save(cart) != null;
	}

}
