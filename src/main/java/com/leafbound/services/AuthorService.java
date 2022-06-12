package com.leafbound.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.leafbound.models.Author;

@Service
@Transactional
public interface AuthorService {
    /*
     * Auto-generated methods
     * save() (insert)
     * update() (update)
     * delete() (delete)
     * findAll() (select all)
     * findById() (select by id)
     */
    public boolean add(Author author);

    public Author getById();

    public List<Author> getAll();

    public boolean edit();

    public boolean remove();
}
