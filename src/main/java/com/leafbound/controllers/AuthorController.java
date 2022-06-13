package com.leafbound.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leafbound.models.AuthorDTO;
import com.leafbound.services.AuthorServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("api/v1")
@Api(value = "Author Controller", tags = "AUTHOR_TAG")
public class AuthorController {

    @Autowired
    private AuthorServiceImpl service;

    private static Logger logger = Logger.getLogger(AuthorController.class);

    @ApiOperation(value = "Create a new author", notes = "Add a new author to the DB")
    @PostMapping(path = "/author")
    public ResponseEntity<String> create(@RequestBody AuthorDTO author) {
        logger.info("Creating author: " + author);
        service.add(author);
        return new ResponseEntity<>("Author created successfully", HttpStatus.CREATED);
    }

}
