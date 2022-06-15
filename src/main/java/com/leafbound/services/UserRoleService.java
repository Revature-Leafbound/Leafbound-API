package com.leafbound.services;

import com.leafbound.models.UserRole;

public interface UserRoleService {

    public boolean add(UserRole userRole);

    public UserRole getById(int id);

    public boolean remove(int id);

}
