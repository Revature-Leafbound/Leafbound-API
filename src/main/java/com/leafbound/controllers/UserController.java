package com.leafbound.controllers;

import java.security.InvalidKeyException;
import java.util.List;
import java.util.UUID;

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

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
@Api(value = "UserRollController", tags = "USER COLLECTIONS")
public class UserController {

	private static Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl service;

	@Autowired
	private JwtServiceImpl jwtService;

	@GetMapping("/GetUser")
	public @ResponseBody User readById(UUID id) {
		log.info("Getting user with id: " + id);
		return service.getUserById(id);
	}

	@GetMapping("/GetUsers")
	public @ResponseBody List<User> readAllUsers() {
		log.info("Getting all users");
		return service.getAllUsers();
	}

	@PatchMapping("/UpdateUser")
	public @ResponseBody String updateUser(@RequestBody User user) {
		log.info("Updating user");

		return (service.updateUser(user)) ? "Update successful" : "Update failed";
	}

	@DeleteMapping("/DeleteUser/{id}")
	public @ResponseBody String deleteUser(@RequestHeader("Authorization") String authorization,
			@PathVariable String id) {
		log.info("Deleting user");

		try {
			UserDTO userDTO = jwtService.getDTO(authorization.replace("Bearer ", ""));

			if (userDTO != null && userDTO.getRole().getRole().equals("admin")) {
				return service.deleteUser(id) ? "DELETION_SUCCESSFUL" : "DELETION_FAILED";
			} else {
				return "DELETION_FAILED";
			}
		} catch (InvalidKeyException e) {
			return "DELETION_FAILED";
		}
		// TODO: Return proper response
	}

	@GetMapping("/Login")
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
			responseHeaders.set("X-Auth-Token", "Bearer " + jwt);
			responseHeaders.set("Access-Control-Expose-Headers", "X-Auth-Token");
		} catch (InvalidKeyException e) {
			log.debug("Error in login: " + e.getMessage());
		}

		// Return a created status
		return ResponseEntity.ok()
				.headers(responseHeaders)
				.body("Login successful");
	}

	@PostMapping("/Register")
	public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
		log.info("Register user");

		// Create a new HttpHeader object
		HttpHeaders responseHeaders = new HttpHeaders();

		// Goal confirm the credential form the request
		userDTO = service.createUser(userDTO);

		// Create a jwt to send back
		try {
			String jwt = jwtService.createJWT(userDTO);
			responseHeaders.set("X-Auth-Token", "Bearer " + jwt);
			responseHeaders.set("Access-Control-Expose-Headers", "X-Auth-Token");
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
