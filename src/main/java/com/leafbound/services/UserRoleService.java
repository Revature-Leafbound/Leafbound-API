package com.leafbound.services;

package com.leafbound.models.UserRole;

import java.util.List;

public interface UserRoleService {

    public boolean add(UserRole userRole);

    public UserRole getById(int id);

    public boolean remove(int id);

}
