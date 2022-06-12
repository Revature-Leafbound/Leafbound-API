package com.leafbound.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leafbound.models.Author;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
