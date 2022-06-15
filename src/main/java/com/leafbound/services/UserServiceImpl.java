package com.leafbound.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbound.models.User;
import com.leafbound.models.UserDTO;
import com.leafbound.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository repository;

	@Override
	public UserDTO createUser(UserDTO userDTO) {

		// Create an empty user model
		User user = new User();

		// Update the user from the DTOw
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setRoleId(userDTO.getRoleId());

		// Save the user to the DB
		UUID pk = repository.save(user).getId();

		// Return the user DTO with the PK
		userDTO.setId(pk);
		return userDTO;
	}

	@Override
	public User getUserById(UUID id) {
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
	}

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public boolean updateUser(User user) {
		User target = this.getUserById(user.getId());
		target.setFirstName(user.getFirstName());
		target.setLastName(user.getLastName());
		target.setPassword(user.getPassword());
		target.setEmail(user.getEmail());
		target.setRoleId(user.getRoleId());
		return (repository.save(target) != null);
	}

	@Override
	public boolean deleteUser(UUID id) {
		try {
			this.getUserById(id);
			repository.deleteById(id);
		} catch (IllegalArgumentException e) {
			logger.warn("Unable to delete user: " + e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public UserDTO login(UserDTO userDTO) throws IllegalArgumentException {

		// Get the user from the DB
		User user = repository.findByEmail(userDTO.getEmail());

		// Check if the user exists
		if (user == null) {
			throw new IllegalArgumentException("User not found");
		}

		// Check if the password is correct
		if (!user.getPassword().equals(userDTO.getPassword())) {
			throw new IllegalArgumentException("Invalid password");
		}

		// Set the userDTO id
		userDTO.setId(user.getId());

		// Return the userDTO
		return userDTO;
	}

}
