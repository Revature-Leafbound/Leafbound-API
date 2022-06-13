package com.leafbound.repositories;

import java.util.UUID;

import javax.transaction.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leafbound.models.User;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {

	
}
