package com.leafbound.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.leafbound.models.Order;

public interface OrderService {

	Order getOrderById(String id);

	Order getOrderByDate(String orderDate);

	List<Order> getAllOrders();

	boolean updateOrder(String id, Order order);

	boolean deleteOrder(int id);

}
