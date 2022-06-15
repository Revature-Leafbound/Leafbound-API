package com.leafbound.repositories;

import javax.transaction.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leafbound.models.UserRole;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository <UserRole, Integer> {

}
