package com.leafbound.services;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leafbound.models.User;
import com.leafbound.models.UserDTO;
import com.leafbound.models.UserRole;
import com.leafbound.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserRoleServiceImpl userRoleService;

	@Override
	public UserDTO createUser(UserDTO userDTO) {

		// Create an empty user model
		User user = new User();

		// Update the user from the DTO
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setUserRole(userRoleService.getById(userDTO.getRoleId()));

		// Save the user to the DB
		UUID pk = repository.save(user).getId();

		// Return the user DTO with the PK
		userDTO.setId(pk);
		return userDTO;
	}

	/**
	 * Update the user with the given ID.
	 * 
	 * @param id The ID of the user to update.
	 */
	@Override
	public User getUserById(String id) {
		UUID uuid = UUID.fromString(id);
		return repository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("User not found"));
	}

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public boolean updateUser(UserDTO userDTO) {

		// Get the user from the DB
		User target = this.getUserById(userDTO.getId().toString());

		UserRole userRole = userRoleService.getById(userDTO.getRoleId());

		// Update the user
		target.setFirstName(userDTO.getFirstName());
		target.setLastName(userDTO.getLastName());
		target.setPassword(userDTO.getPassword());
		target.setEmail(userDTO.getEmail());
		target.setUserRole(userRole);

		// Save the user to the DB
		return (repository.save(target) != null);
	}

	@Override
	public boolean deleteUser(String id) {
		UUID userAsUUID = UUID.fromString(id);
		try {
			repository.deleteById(userAsUUID);
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
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setRoleId(user.getUserRole().getId());

		// Return the userDTO
		return userDTO;
	}

}
