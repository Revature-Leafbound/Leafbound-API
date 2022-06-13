package com.leafbound.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbound.models.User;
import com.leafbound.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository repository;

	@Override
	public boolean createUser(User user) {
		UUID pk = repository.save(user).getId();
		return (pk != null);
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
		target.setSettingsId(user.getSettingsId());
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
	public User login(User user) {
		return null;
	}

	@Override
	public User register(User user) {
		return null;
	}

}
