package com.leafbound.services;

import java.util.List;

import com.leafbound.models.Order;

public interface OrderService {

	public boolean add(Order order);

	Order getOrderById(String id);

	Order getOrderByDate(String orderDate);

	List<Order> getAllOrders();

	List<Order> getOrderByCustomerId(String customerId);

	boolean updateOrder(String id, Order order);

	boolean deleteOrder(int id);

}
