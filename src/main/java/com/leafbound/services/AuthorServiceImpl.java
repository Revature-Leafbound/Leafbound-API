package com.leafbound.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.leafbound.models.Author;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    /*
     * Auto-generated methods
     * save() (insert)
     * update() (update)
     * delete() (delete)
     * findAll() (select all)
     * findById() (select by id)
     */
    public boolean add() {
        return false;
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
