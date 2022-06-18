package com.leafbound.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
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

	private static Logger log = Logger.getLogger(OrderServiceImpl.class);

	@Override
	public boolean add(Order order) {
		log.info("Adding order");
		return orepo.save(order) != null;
	}

	@Override
	public Order getOrderById(String id) {

		UUID uuid = UUID.fromString(id);

		return orepo.findById(uuid).orElseThrow(() -> new RuntimeException("Order not found in DB"));

	}

	@Override
	public Order getOrderByDate(String orderDate) {
		// Create a date fomatter
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

		// Parse the date
		LocalDate date = LocalDate.parse(orderDate);

		// Find the order
		return orepo.findByDate(date);
	}

	@Override
	public List<Order> getAllOrders() {

		return orepo.findAll();
	}

	@Override
	public boolean updateOrder(String id, Order order) {
		Order target = this.getOrderById(id);
		target.setOrderDate(order.getOrderDate());
		return (orepo.save(target) != null);
	}

	@Override
	public boolean deleteOrder(int id) {
		UUID uuid = UUID.fromString(String.valueOf(id));
		orepo.deleteById(uuid);
		return true;
	}

}
