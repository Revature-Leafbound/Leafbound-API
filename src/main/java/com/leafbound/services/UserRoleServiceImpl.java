package com.leafbound.services;

import java.util.List;

import com.leafbound.models.UserRole;
import com.leafbound.repositories.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository repository;

    @Override
    public List<UserRole> getAll() {
        return repository.findAll();
    }

    @Override
    public UserRole getById(int id) {
        return repository.findById(id);
    }

    @Override
    public boolean add(UserRole userRole) {
        int pk = repository.save(userRole).getId();
        return (pk > 0) ? true : false;
    }

    // @Override
    // public boolean edit(int id, UserRole userRole) {
    //     UserRole target = repository.findById(id);
    //     target.setRole(userRole.getRole());
    //     return (repository.save(target) != null) ? true : false;
    // }

    @Override
    public boolean remove(int id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            return false;
        }
        return true;

}

