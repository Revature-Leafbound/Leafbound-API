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
        // This line would return the newly created author. We only wnat to return a
        // boolean.
        // return authorRepository.save(author);

        // Create a new author and obtain the primary key.
        int primaryKey = authorRepository.save(author).getId();

        // Return true if the primary key is greater than 0, otherwise, return false.
        return (primaryKey > 0) ? true : false;
    }

    public Author getById() {
        return null;
    }

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
