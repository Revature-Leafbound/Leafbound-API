package com.leafbound.repositories;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leafbound.models.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    User findByEmail(String email);
}
