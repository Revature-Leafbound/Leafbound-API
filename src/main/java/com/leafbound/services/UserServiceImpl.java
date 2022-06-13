package com.leafbound.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbound.models.User;
import com.leafbound.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public boolean createUser(User user) {
		UUID pk = repository.save(user).getId();
		return (pk != null) ? true : false;
	}

	@Override
	public User getUserById(UUID id) {
		
		return repository.findById(id).orElseThrow(() -> 
		new IllegalArgumentException("User not found"));
		
//		Optional<User> optional = repository.findById(id);
//		
//		
//		if (optional.isPresent()) {
//			User user = optional.get();
//			return user;
//		} else {
//			throw new IllegalArgumentException("User not found");
//		}
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User Register(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
