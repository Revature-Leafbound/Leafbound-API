package com.leafbound.services;

import java.math.BigInteger;
import java.time.LocalDate;
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

	@Autowired
	public OrderServiceImpl(OrderRepository orepo) {
		this.orepo = orepo;
	}

	@Override
	public boolean add(Order order) {
		log.info("Adding order");
		return orepo.save(order) != null;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Order getOrderById(String id) {
		
		log.info("In service layer, getting order by ID:" + id);
		
		String Id = id.replace("-", "");
		UUID uuid = new UUID(
		        new BigInteger(Id.substring(0, 16), 16).longValue(),
		        new BigInteger(Id.substring(16), 16).longValue());

		return orepo.getById(uuid);
	
//		UUID uuid = UUID.fromString(id);
//
//		return orepo.findById(uuid).orElseThrow(() -> new RuntimeException("Order not found"));
		
	}
	
	@Override
	public List<Order> getOrderByDate(String orderDate) {
		log.info("finding order by date in service...");

		// Parse the date
		LocalDate date = LocalDate.parse(orderDate);

		//
		log.info("Returning order by date: " + date);

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
	public boolean deleteOrder(UUID id) {
		orepo.deleteById(id);
		return true;
	}


//	@Override
//	public List<Order> getOrderByCustomerId(String customerId) {
//		
//		customerId.replace("-", "");
//		UUID uuid = new UUID(
//		        new BigInteger(customerId.substring(0, 16), 16).longValue(),
//		        new BigInteger(customerId.substring(16), 16).longValue());
//		return orepo.findByCustomerId(uuid);
//	}


}