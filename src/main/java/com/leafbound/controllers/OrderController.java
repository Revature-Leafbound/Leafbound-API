package com.leafbound.controllers;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leafbound.models.Order;
import com.leafbound.models.UserDTO;
import com.leafbound.services.JwtServiceImpl;
import com.leafbound.services.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "OrderRestController", tags = "ORDER COLLECTION")
public class OrderController {

	private static Logger log = Logger.getLogger(OrderController.class);

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private OrderService oserv;

	// Lock the order
	@PostMapping(path = "/order")
	@ApiOperation(value = "Create a new order", notes = "Create a new order")
	public @ResponseBody boolean createOrder(@RequestBody Order order) {
		log.info("Creating order");
		return oserv.add(order);
	}

	// Lock this one
	@GetMapping(path = "/order/{id}")
	@ApiOperation(value = "Getting order by Id")
	public @ResponseBody Order getById(@PathVariable String id) {

		log.info("finding order by Id in controller...");

		return oserv.getOrderById(id);

	}

	@GetMapping(path = "/order/date/{orderDate}")
	@ApiOperation(value = "Getting order by date")
	public @ResponseBody List<Order> getByDate(@PathVariable String orderDate) {
		// TODO: Are we using this route?
		log.info("finding order by date in controller...");

		return oserv.getOrderByDate(orderDate);
	}

	// The example of how to use the JWT token
	@GetMapping(path = "/order/customer/{customerId}")
	@ApiOperation(value = "Getting order by customer")
	public ResponseEntity<List<Order>> getByCustomer(@PathVariable String customerId,
			@RequestHeader("Authorization") String token) {
		log.info("finding order by customer in controller...");

		// Set header for the response
		HttpHeaders responseHeader = new HttpHeaders();

		// Check the token
		UserDTO userDTO = jwtService.getDTO(token);

		if (userDTO == null) {
			log.info("Invalid token");
			responseHeader.add("message", "Invalid token");
			return ResponseEntity.status(401).headers(responseHeader).build();
		}

		return ResponseEntity.ok(oserv.getOrderByCustomerId(customerId));
	}

	// Lock this one
	@GetMapping(path = "/order/all")
	@ApiOperation(value = "Getting all orders")
	public @ResponseBody List<Order> getAll() {

		log.info("finding all orders by Id in controller...");

		return oserv.getAllOrders();

	}

	// Lock this one
	@PatchMapping("/order/{id}")
	@ApiOperation(value = "Update order entity")
	public @ResponseBody boolean updateOrder(@PathVariable String id, @RequestBody Order order) {

		log.info("updating an order in controller...");

		return oserv.updateOrder(id, order);
	}

	// Lock this one Check if ADMIN or CUSTOMER
	@DeleteMapping("/order/{id}")
	@ApiOperation(value = "Remove order entity")
	public @ResponseBody boolean deleteOrder(@PathVariable UUID id) {

		log.info("deleting an order in controller...");
		return oserv.deleteOrder(id);
	}

}