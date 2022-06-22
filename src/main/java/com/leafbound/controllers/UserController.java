package com.leafbound.controllers;

import java.security.InvalidKeyException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.leafbound.models.User;
import com.leafbound.models.UserDTO;
import com.leafbound.services.JwtServiceImpl;
import com.leafbound.services.UserServiceImpl;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
@Api(value = "UserController", tags = "USER COLLECTION")
public class UserController {

	private static Logger log = Logger.getLogger(UserController.class);

	private String X_AUTH_TOKEN = "X-Auth-Token";

	@Autowired
	private UserServiceImpl service;

	@Autowired
	private JwtServiceImpl jwtService;

	@GetMapping("/user/{id}")
	public @ResponseBody User readById(@PathVariable String id) {
		log.info("Getting user with id: " + id);
		return service.getUserById(id);
	}

	@GetMapping("/user/all")
	public @ResponseBody List<User> readAllUsers() {
		log.info("Getting all users");
		return service.getAllUsers();
	}

	@PatchMapping("/user")
	public @ResponseBody String updateUser(@RequestBody UserDTO userDTO) {
		log.info("Updating user");

		return (service.updateUser(userDTO)) ? "Update successful" : "Update failed";
	}

	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@RequestHeader("Authorization") String authorization,
			@PathVariable String id) {
		log.info("Deleting user");

		// TODO: Rework the lockout
		// UserDTO userDTO = jwtService.getDTO(authorization.replace("Bearer ", ""));

		return service.deleteUser(id) ? "DELETION_SUCCESSFUL" : "DELETION_FAILED";
	}

	@PostMapping("/user/login")
	public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {

		// Create a new HttpHeader object
		HttpHeaders responseHeaders = new HttpHeaders();

		// Goal confirm the credential form the request
		try {
			// Update the current userDTO with a completed userDTO
			userDTO = service.login(userDTO);
		} catch (IllegalArgumentException e) {
			log.error("Invalid credentials");
			// Return a bad request
			return new ResponseEntity<>("Unauthorized user", HttpStatus.UNAUTHORIZED);
		}

		// create a jwt to send back
		try {
			// Create a string for the JWT
			String jwt = jwtService.createJWT(userDTO);

			// Set Headers
			responseHeaders.set(X_AUTH_TOKEN, "Bearer " + jwt);
			responseHeaders.set("Access-Control-Expose-Headers", X_AUTH_TOKEN);
		} catch (InvalidKeyException e) {
			log.debug("Error in login: " + e.getMessage());
		}

		// Return a created status
		return ResponseEntity.ok()
				.headers(responseHeaders)
				.body("Login successful");
	}

	@PostMapping("/user/register")
	public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
		log.info("Register user");

		// Create a new HttpHeader object
		HttpHeaders responseHeaders = new HttpHeaders();

		// Goal confirm the credential form the request
		userDTO = service.createUser(userDTO);

		// Create a jwt to send back
		try {
			String jwt = jwtService.createJWT(userDTO);
			responseHeaders.set(X_AUTH_TOKEN, "Bearer " + jwt);
			responseHeaders.set("Access-Control-Expose-Headers", X_AUTH_TOKEN);
		} catch (InvalidKeyException e) {
			log.debug("Register JWT threw an error " + e.getMessage());

			// Return a bad request
			return new ResponseEntity<>("Unauthorized user", HttpStatus.UNAUTHORIZED);
		}

		// Return a created status
		return ResponseEntity.ok()
				.headers(responseHeaders)
				.body("Registration successful");

	}
}
