package com.leafbound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leafbound.models.UserRole;
import com.leafbound.repositories.UserRoleRepository;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository repository;

    @Override
    public UserRole getById(int id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public boolean add(UserRole userRole) {
        int pk = repository.save(userRole).getId();
        return (pk > 0);
    }

    @Override
    public boolean remove(int id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
