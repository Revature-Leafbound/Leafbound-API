package com.leafbound.services;

import com.leafbound.models.Cart;

public interface CartService {

	public boolean addtoCart(Cart cart);

	public boolean deleteCart(int id);
}
