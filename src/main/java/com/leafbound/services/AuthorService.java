package com.leafbound.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.leafbound.models.Author;

@Service
@Transactional
public interface AuthorService {

    public boolean add(Author author);

    public Author getById(int id);

    public List<Author> getAll();

    public boolean edit(int id, Author author);

    public boolean remove(int id);
}
