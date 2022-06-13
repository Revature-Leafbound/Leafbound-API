package com.leafbound.services;

import java.util.List;
import java.util.UUID;

import com.leafbound.models.User;

public interface UserService {
	boolean createUser(User user);
	
	User getUserById(UUID id);
	
	List<User> getAllUsers();
	
	boolean updateUser(User user);
	
	boolean deleteUser(User user);
	
	User login(User user);
	
	User Register(User user);
}
