package com.leafbound.services;

import java.util.List;
import java.util.UUID;

import com.leafbound.models.Order;

public interface OrderService {
	
	Order getOrderById(UUID id);
	
	Order getOrderByDate(Order order);
	
	List<Order> getAllOrders();
	
	boolean updateOrder(Order order);
	
	boolean deleteOrder(Order order);

}
