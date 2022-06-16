package com.leafbound.services;

import java.util.List;
import java.util.UUID;

import com.leafbound.models.User;
import com.leafbound.models.UserDTO;

public interface UserService {
	UserDTO createUser(UserDTO userDTO);

	User getUserById(UUID id);

	List<User> getAllUsers();

	boolean updateUser(User user);

	boolean deleteUser(String id);

	UserDTO login(UserDTO userDTO);
}
