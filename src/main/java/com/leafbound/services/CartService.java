package com.leafbound.services;

import com.leafbound.models.Carts;

public interface CartService {
	
	//find products by id
	public Products findProdById(int id);
	
	//update cart
	public boolean updateCart(Carts cart);
	
	//delete cart
	public boolean deleteCart(int id);
}
