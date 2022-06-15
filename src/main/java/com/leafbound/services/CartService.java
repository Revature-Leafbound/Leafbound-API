package com.leafbound.services;

import com.leafbound.models.Carts;

public interface CartService {
	
	public boolean addtoCart(Carts cart);
	
	public Carts deleteCart(int id);
}
