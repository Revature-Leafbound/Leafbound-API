package com.leafbound.controllers;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RestController;

import com.leafbound.models.ClientMessage;
import com.leafbound.models.Order;
import com.leafbound.models.OrderDTO;
import com.leafbound.models.UserDTO;
import com.leafbound.services.JwtServiceImpl;
import com.leafbound.services.OrderService;
import com.leafbound.util.ClientMessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "OrderRestController", tags = "ORDER COLLECTION")
public class OrderController {

	private static Logger log = Logger.getLogger(OrderController.class);
	
	@Autowired
	private ModelMapper modelMapper; 

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private OrderService oserv;

	// Lock the order
	@PostMapping(path = "/order")
	@ApiOperation(value = "Create a new order", notes = "Create a new order")
	public ResponseEntity<ClientMessage> createOrder(@RequestBody OrderDTO orderDto,
			@RequestHeader("Authorization") String token) {
		log.info("Creating order");

		// Remove the "Bearer " from the token
		token = token.substring(7);

		// Check the token
		UserDTO userDTO = jwtService.getDTO(token);

		if (userDTO == null) {
			return invalidUserError();
		}
		
		// Convert DTO object to entity object using ModelMapper
		Order order = modelMapper.map(orderDto, Order.class);
		
		ClientMessage clientMessage = oserv.add(order) ? ClientMessageUtil.CREATION_SUCCESSFUL
				: ClientMessageUtil.CREATION_FAILED;
		return ResponseEntity.ok(clientMessage);
	}

	// Lock this one
	@GetMapping(path = "/order/{id}")
	@ApiOperation(value = "Getting order by Id")
	public ResponseEntity<Order> getById(@PathVariable String id, @RequestHeader("Authorization") String token) {

		log.info("finding order by Id in controller...");

		// Remove the "Bearer " from the token
		token = token.substring(7);

		// Check the token
		UserDTO userDTO = jwtService.getDTO(token);

		if (userDTO == null) {
			// Set header for the response
			HttpHeaders responseHeader = new HttpHeaders();
			log.info("Invalid token");
			responseHeader.add("message", "Invalid token");
			return ResponseEntity.status(401).headers(responseHeader).build();
		}
		return ResponseEntity.ok(oserv.getOrderById(id));

	}

	@GetMapping(path = "/order/date/{orderDate}")
	@ApiOperation(value = "Getting order by date")
	public ResponseEntity<List<Order>> getByDate(@PathVariable String orderDate,
			@RequestHeader("Authorization") String token) {
		// TODO: Are we using this route?
		log.info("finding order by date in controller...");
		
		// Remove the "Bearer " from the token
				token = token.substring(7);

				// Check the token
				UserDTO userDTO = jwtService.getDTO(token);

				if (userDTO == null) {
					// Set header for the response
					HttpHeaders responseHeader = new HttpHeaders();
					log.info("Invalid token");
					responseHeader.add("message", "Invalid token");
					return ResponseEntity.status(401).headers(responseHeader).build();
				}

		return ResponseEntity.ok(oserv.getOrderByDate(orderDate));
	}

	// The example of how to use the JWT token
	@GetMapping(path = "/order/customer/{customerId}")
	@ApiOperation(value = "Getting order by customer")
	public ResponseEntity<List<Order>> getByCustomer(@PathVariable String customerId,
			@RequestHeader("Authorization") String token) {
		log.info("finding order by customer in controller...");

		// Remove the "Bearer " from the token
		token = token.substring(7);

		// Check the token
		UserDTO userDTO = jwtService.getDTO(token);

		if (userDTO == null) {
			// Set header for the response
			HttpHeaders responseHeader = new HttpHeaders();
			log.info("Invalid token");
			responseHeader.add("message", "Invalid token");
			return ResponseEntity.status(401).headers(responseHeader).build();
		}

		return ResponseEntity.ok(oserv.getOrderByCustomerId(customerId));
	}

	// Lock this one
	@GetMapping(path = "/order/all")
	@ApiOperation(value = "Getting all orders")
	public ResponseEntity<List<Order>> getAll(@RequestHeader("Authorization") String token) {

		log.info("finding all orders by Id in controller...");

		// Remove the "Bearer " from the token
		token = token.substring(7);

		// Check the token
		UserDTO userDTO = jwtService.getDTO(token);

		if (userDTO == null) {
			// Set header for the response
			HttpHeaders responseHeader = new HttpHeaders();
			log.info("Invalid token");
			responseHeader.add("message", "Invalid token");
			return ResponseEntity.status(401).headers(responseHeader).build();
		}

		return ResponseEntity.ok(oserv.getAllOrders());

	}

	// Lock this one
	@PatchMapping("/order/{id}")
	@ApiOperation(value = "Update order entity")
	public ResponseEntity<ClientMessage> updateOrder(@PathVariable String id, @RequestBody OrderDTO orderDto,
			@RequestHeader("Authorization") String token) {
		log.info("updating an order in controller...");

		// Remove the "Bearer " from the token
		token = token.substring(7);

		// Check the token
		UserDTO userDTO = jwtService.getDTO(token);

		if (userDTO == null) {
			return invalidUserError();
		}
		
		// Convert DTO object to entity object using ModelMapper
		Order order = modelMapper.map(orderDto, Order.class);
		
		ClientMessage clientMessage = oserv.updateOrder(id, order) ? ClientMessageUtil.UPDATE_SUCCESSFUL
				: ClientMessageUtil.UPDATE_FAILED;
		return ResponseEntity.ok(clientMessage);
	}

	// Lock this one Check if ADMIN or CUSTOMER
	@DeleteMapping("/order/{id}")
	@ApiOperation(value = "Remove order entity")
	public ResponseEntity<ClientMessage> deleteOrder(@PathVariable UUID id,
			@RequestHeader("Authorization") String token) {
		log.info("updating an order in controller...");

		// Remove the "Bearer " from the token
		token = token.substring(7);

		// Check the token
		UserDTO userDTO = jwtService.getDTO(token);

		if (userDTO == null) {
			return invalidUserError();
		}
		ClientMessage clientMessage = oserv.deleteOrder(id) ? ClientMessageUtil.DELETION_SUCCESSFUL
				: ClientMessageUtil.DELETION_FAILED;
		return ResponseEntity.ok(clientMessage);
	}

	protected ResponseEntity<ClientMessage> invalidUserError() {
		// Set header for the response
		HttpHeaders responseHeader = new HttpHeaders();
		log.info("Invalid token");
		responseHeader.add("message", "Invalid token");
		return ResponseEntity.status(401).headers(responseHeader).build();
	}

}