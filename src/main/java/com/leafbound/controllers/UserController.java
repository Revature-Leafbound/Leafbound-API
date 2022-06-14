package com.leafbound.controllers;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leafbound.models.User;
import com.leafbound.services.UserService;

import io.swagger.annotations.Api;

@CrossOrigin(origins ="http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
@Api(value= "UserRollController", tags = "USER COLLECTIONS")
public class UserController {
	
	private static Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService service;
	
	@PostMapping("/CreateUser")
	public String createUser(@RequestBody User user) {
		log.info("Creating user");
		
		return (service.createUser(user)) ? "User created successfully": "Error creating user";
		
	}

	@GetMapping("/GetUser")
	public User readById(UUID id) {
		log.info("Getting user with id: " + id);
		return service.getUserById(id);
	}
	
	@GetMapping("/GetUsers")
	public List<User> readAllUsers(){
		log.info("Getting all users");
		return service.getAllUsers();
	}
	
	@PatchMapping("/UpdateUser")
	public String updateUser(@RequestBody User user) {
		log.info("Updating user");		
		
		return (service.updateUser(user)) ? "Update successful": "Update failed";
		
	}
	
	@DeleteMapping("/DeleteUser")
	public String deleteUser(@RequestBody UUID id) {
		log.info("Deleting user");	
		return (service.deleteUser(id)) ? "Delete successful": "Delete failed";
		
	}
	
	@GetMapping("/Login")
	public User login(@RequestBody User user) {
		log.info("Loggin in");	
		return service.login(user);
		
	}
	
	@PostMapping("/Register")
	public User register(@RequestBody User user) {
		log.info("Register user");	
		return service.register(user);
		
	}
}
