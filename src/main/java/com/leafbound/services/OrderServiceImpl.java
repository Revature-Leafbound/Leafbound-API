package com.leafbound.services;

import java.time.LocalDate;
import java.util.ArrayList;
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
	public List<Order> getOrderByDate(String orderDate) {
		log.info("finding order by date in service...");

		// Parse the date
		LocalDate date = LocalDate.parse(orderDate);

		//
		log.info("Returning order by date: " + date);

		// Find the order
		List<Order> order = orepo.findByDate(date);

		return order;

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
	public boolean deleteOrder(UUID id) {
		orepo.deleteById(id);
		return true;
	}

	@Override
	public List<Order> getOrderByCustomerId(String customerId) {
		UUID uuid = UUID.fromString(customerId);
		return orepo.findByCustomerId(uuid);
	}

}
