package com.leafbound.services;

import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean add(Author author) {
        // This line would return the newly created author; we want a boolean.
        // return authorRepository.save(author);

        // Create a new author and obtain the primary key.
        int primaryKey = authorRepository.save(author).getId();

        // Return true if the primary key is greater than 0, otherwise, return false.
        return (primaryKey > 0) ? true : false;
    }

    @Override
    public Author getById(int id) {
        // The default method form Jpa returns an Optional<Object>
        Optional<Author> author = authorRepository.findById(id);

        // If the author is present, return it, otherwise, return null.
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new IllegalArgumentException("Author not found.");
        }
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public boolean remove() {
        return false;
    }
}
