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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.leafbound.models.User;
import com.leafbound.models.UserDTO;
import com.leafbound.services.JwtService;
import com.leafbound.services.UserService;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
@Api(value = "UserRollController", tags = "USER COLLECTIONS")
public class UserController {

	private static Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private UserService service;

	@Autowired
	private JwtService jwtService;

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

	@DeleteMapping("/DeleteUser")
	public @ResponseBody String deleteUser(@RequestBody UUID id) {
		log.info("Deleting user");
		return (service.deleteUser(id)) ? "Delete successful" : "Delete failed";

	}

	@GetMapping("/Login")
	public @ResponseBody User login(@RequestBody UserDTO userDTO) {
		
		// Create a new HttpHeader object
		HttpHeaders headers = new HttpHeaders();

		// Goal confirm the credential form the request
	
			// Update the current userDTO with a completed userDTO
			userDTO = service.login(userDTO);
	

		
		// create a jwt to send back
		try{
			//Create a string for the JWT
			//createJwt(UserDTO userDTO)
			String jwt = jwtService.createJWT(userDTO);


		}
		
	}

	@PostMapping("/Register")
	public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
		log.info("Register user");

		// Create a new HttpHeader object
		HttpHeaders responseHeaders = new HttpHeaders();

		// Goal confirm the credential form the request
		userDTO = service.createUser(userDTO);

		// create a jwt to send back
		try {
			String jwt = jwtService.createJwt(userDTO);
			responseHeaders.set("X-Auth-Token", "Bearer " + jwt);
			responseHeaders.set("Access-Control-Expose-Headers", "X-Auth-Token");
		} catch (InvalidKeyException | JsonProcessingException e) {
			log.debug("Register JWT threw an error " + e.getMessage());

			// Return a bad request
			return new ResponseEntity<>("Unauthorized user", HttpStatus.UNAUTHORIZED);
		}

		// Return a created status
		return ResponseEntity.ok()
				.headers(responseHeaders)
				.body("Login successful");

	}
}
