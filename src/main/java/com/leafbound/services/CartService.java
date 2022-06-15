package com.leafbound.services;

import com.leafbound.models.Carts;

public interface CartService {
	
	public Carts findById(int id);
	
	public boolean addtoCart(Carts cart);
	
	public boolean updateCart(Carts cart);
	
	public Carts deleteCart(int id);
}
