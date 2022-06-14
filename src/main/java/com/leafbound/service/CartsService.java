package com.leafbound.service;


import com.leafbound.models.Carts;

public interface CartsService {

	//find by id
	
	public Carts findById(int id);
	
	// add
	public boolean addtoCart(Carts cart);
	
	//update cart
	public boolean updateCart(Carts cart);
	
	//delete cart
	public boolean deleteCart(Carts cart);
	

}
