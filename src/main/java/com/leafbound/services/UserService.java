package com.leafbound.services;

import java.util.List;
import java.util.UUID;

import com.leafbound.models.User;
import com.leafbound.models.UserDTO;

public interface UserService {
	UserDTO createUser(UserDTO userDTO);

	User getUserById(String id);

	List<User> getAllUsers();

	boolean updateUser(UserDTO userDTO);

	boolean deleteUser(String id);

	UserDTO login(UserDTO userDTO);
}
