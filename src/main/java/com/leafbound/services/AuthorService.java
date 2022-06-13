package com.leafbound.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.leafbound.models.AuthorDTO;

@Service
@Transactional
public interface AuthorService {

    public boolean add(AuthorDTO authorDto);

    public AuthorDTO getById(int id);

    public List<AuthorDTO> getAll();

    public boolean edit(int id, AuthorDTO authorDto);

    public boolean remove(int id);
}
