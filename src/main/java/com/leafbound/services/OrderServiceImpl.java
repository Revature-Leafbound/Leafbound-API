package com.leafbound.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leafbound.models.Order;
import com.leafbound.repositories.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orepo;

	@Override
	public Order getOrderById(String id) {

		UUID uuid = UUID.fromString(id);

		return orepo.findById(uuid).orElseThrow(() -> new RuntimeException("Order not found"));

	}

	@Override
	public Order getOrderByDate(LocalDate orderDate) {

		return orepo.findByDate(orderDate);
	}

	@Override
	public List<Order> getAllOrders() {

		return orepo.findAll();
	}

	@Override
	public boolean updateOrder(Order order) {
		Order target = this.getOrderById(order.getId().toString());
		target.setOrderDate(order.getOrderDate());
		return (orepo.save(target) != null);
	}

	@Override
	public boolean deleteOrder(Order order) {
		orepo.delete(order);
		return true;
	}

}
