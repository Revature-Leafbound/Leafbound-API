package com.leafbound.services;

package com.leafbound.models.UserRole;

import java.util.List;

public interface UserRoleService {

    public List<UserRole> getAll();

    public boolean add(UserRole userRole);

    public UserRole getById(int id);

    //public boolean edit(int id, UserRole userRole);

    public boolean remove(int id);

}