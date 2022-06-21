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
	public Order getOrderByDate(LocalDate orderDate) {
		
		log.info("In service layer, getting order by LocalDate");

		return orepo.findByDate(orderDate);
	}

	@Override
	public List<Order> getAllOrders() {
		
		log.info("In service layer, getting all order");

		return orepo.findAll();
	}

	@Override
	public boolean updateOrder(Order order) {
		
		log.info("In service layer, updating order");
		
		Order target = this.getOrderById(order.getId().toString());
		target.setOrderDate(order.getOrderDate());
		return (orepo.save(target) != null);
	}

	@Override
	public boolean deleteOrder(Order order) {
		
		log.info("In service layer, deleting order");
		
		orepo.delete(order);
		return true;
	}

	

}