package com.leafbound.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.leafbound.models.Order;
import com.leafbound.repositories.OrderRepository;

public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orepo;
	
	private static Logger log = Logger.getLogger(OrderServiceImpl.class);

	@Override
	public Order getOrderById(UUID id) {
		
		return orepo.findById(id).get();
		
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
		Order target = this.getOrderById(order.getId());
		target.setOrderDate(order.getOrderDate());
		return (orepo.save(target)!= null);
	}

	@Override
	public boolean deleteOrder(Order order) {
		orepo.delete(order);
		return true;
	}


	

	

}
