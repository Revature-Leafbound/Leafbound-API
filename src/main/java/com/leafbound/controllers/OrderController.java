package com.leafbound.controllers;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leafbound.models.Order;
import com.leafbound.services.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "OrderRestController", tags = "ORDER COLLECTION")
public class OrderController {

	private static Logger log = Logger.getLogger(OrderController.class);

	@Autowired
	private OrderService oserv;

	@GetMapping(path = "/orderid/{id}")
	@ApiOperation(value = "Getting order by Id")
	public @ResponseBody Order getById(@PathVariable String id) {

		log.info("finding order by Id in controller...");

		return oserv.getOrderById(id);

	}

	@GetMapping(path = "/orderdate")
	@ApiOperation(value = "Getting order by date")
	public @ResponseBody Order getByDate() {

		log.info("finding order by date in controller...");

		return oserv.getOrderByDate(null);
	}

	@GetMapping(path = "/orderall")
	@ApiOperation(value = "Getting all orders")
	public @ResponseBody List<Order> getAll() {

		log.info("finding all orders by Id in controller...");

		return oserv.getAllOrders();

	}

	@PutMapping("/orderupdate")
	@ApiOperation(value = "Update order entity")
	public @ResponseBody boolean updateOrder(@RequestBody Order order) {

		log.info("updating an order in controller...");

		return oserv.updateOrder(order);
	}

	@DeleteMapping("/orderdelete")
	@ApiOperation(value = "Remove order entity")

	public @ResponseBody boolean deleteOrder(@RequestBody Order order) {

		log.info("deleting an order in controller...");
		return oserv.deleteOrder(order);
	}

}