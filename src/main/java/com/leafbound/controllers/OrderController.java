package com.leafbound.controllers;

import java.util.List;

import static com.leafbound.util.ClientMessageUtil.CREATION_SUCCESSFUL;
import static com.leafbound.util.ClientMessageUtil.CREATION_FAILED;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leafbound.models.ClientMessage;
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

	@PostMapping(path = "/order")
	@ApiOperation(value = "Create a new order", notes = "Create a new order")
	public @ResponseBody boolean createOrder(@RequestBody Order order) {
		log.info("Creating order");
		return oserv.add(order);
	}

	@GetMapping(path = "/order/{id}")
	@ApiOperation(value = "Getting order by Id")
	public @ResponseBody Order getById(@PathVariable String id) {

		log.info("finding order by Id in controller...");

		return oserv.getOrderById(id);

	}

	@GetMapping(path = "/order/date/{orderDate}")
	@ApiOperation(value = "Getting order by date")
	public @ResponseBody Order getByDate(@PathVariable String orderDate) {
		// TODO: Are we using this route?
		log.info("finding order by date in controller...");

		return oserv.getOrderByDate(orderDate);
	}

	@GetMapping(path = "/order/all")
	@ApiOperation(value = "Getting all orders")
	public @ResponseBody List<Order> getAll() {

		log.info("finding all orders by Id in controller...");

		return oserv.getAllOrders();

	}

	@PatchMapping("/order/{id}")
	@ApiOperation(value = "Update order entity")
	public @ResponseBody boolean updateOrder(@PathVariable String id, @RequestBody Order order) {

		log.info("updating an order in controller...");

		return oserv.updateOrder(id, order);
	}

	@DeleteMapping("/order/{id}")
	@ApiOperation(value = "Remove order entity")
	public @ResponseBody boolean deleteOrder(@PathVariable int id) {

		log.info("deleting an order in controller...");
		return oserv.deleteOrder(id);
	}

}