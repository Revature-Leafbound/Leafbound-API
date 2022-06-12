package com.leafbound.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbound.models.Author;
import com.leafbound.repositories.AuthorRepository;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    /*
     * Auto-generated methods
     * save() (insert)
     * update() (update)
     * delete() (delete)
     * findAll() (select all)
     * findById() (select by id)
     */
    public boolean add(Author author) {
        return authorRepository.save(author);
    };

    public Author getById() {
        return null;
    };

    public List<Author> getAll() {
        return null;
    }

    public boolean edit() {
        return false;
    }

    public boolean remove() {
        return false;
    }
}
