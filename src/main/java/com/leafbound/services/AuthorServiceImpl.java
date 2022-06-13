package com.leafbound.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbound.models.Author;
import com.leafbound.models.AuthorDTO;
import com.leafbound.repositories.AuthorRepository;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    private static final String AUTHOR_NOT_FOUND = "Author not found.";

    @Override
    public boolean add(AuthorDTO authorDto) {
        // This line would return the newly created author; we want a boolean.
        // return authorRepository.save(author);

        // Pull fields from the DTO
        String name = authorDto.getName();

        // Create a new author
        Author author = new Author(name);

        // Save the new author and obtain the primary key.
        int primaryKey = authorRepository.save(author).getId();

        // Return true if the primary key is greater than 0, otherwise, return false.
        return (primaryKey > 0);
    }

    @Override
    public AuthorDTO getById(int id) {
        // The default method form Jpa returns an Optional<Object>
        Optional<Author> optional = authorRepository.findById(id);

        // If the author is present, return it, otherwise, return null.
        if (optional.isPresent()) {

            // Get the author from the Optional<Object>
            Author author = optional.get();

            // Create a new DTO
            AuthorDTO authorDto = new AuthorDTO();
            authorDto.setId(author.getId());
            authorDto.setName(author.getName());

            // Return the DTO
            return authorDto;

        } else {
            // Throw exception if the author is not found.
            throw new IllegalArgumentException(AUTHOR_NOT_FOUND);
        }
    }

    @Override
    public List<AuthorDTO> getAll() {
        // Find all the authors.
        List<Author> authors = authorRepository.findAll();

        // Create a new list of DTOs.
        List<AuthorDTO> authorDtos = new ArrayList<>();

        // Loop through the authors and create DTOs.
        for (Author author : authors) {
            AuthorDTO authorDto = new AuthorDTO();
            authorDto.setId(author.getId());
            authorDto.setName(author.getName());
            authorDtos.add(authorDto);
        }

        // Return the list of DTOs.
        return authorDtos;
    }

    @Override
    public boolean edit(int id, AuthorDTO author) {
        // Get the optional<Author> from the repository.
        Optional<Author> optional = authorRepository.findById(id);

        // Check if the author is present.
        if (optional.isPresent()) {
            // Get the author from the optional.
            Author target = optional.get();

            // Update the DB version of author with the changes
            target.setName(author.getName());

            // Commit the changes to the DB.
            authorRepository.save(target);

            // Return true.
            return true;
        } else {
            // Throw exception if the author is not found.
            throw new IllegalArgumentException(AUTHOR_NOT_FOUND);
        }
    }

    @Override
    public boolean remove(int id) {
        // Get the optional<Author> from the repository.
        Optional<Author> optional = authorRepository.findById(id);

        // Check if the author is present.
        if (optional.isPresent()) {
            // Get the author from the optional.
            Author author = optional.get();

            // Delete the author.
            authorRepository.delete(author);

            // Return true.
            return true;
        } else {
            // Throw exception if the author is not found.
            throw new IllegalArgumentException(AUTHOR_NOT_FOUND);
        }
    }
}
