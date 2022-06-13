package com.leafbound.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    // @ApiOperation(value = "Create a new author", notes = "Add a new author to the
    // DB")
    // @PostMapping(path = "/author")
    // public ResponseEntity<String> create(@RequestBody AuthorDTO author) {
    // logger.info("Creating author: " + author);
    // try
    // {
    // Get the requesting user's information from the token
    // UserJwtDTO userDTO = jwtService.getDTO(authorization.replace("Bearer ", ""));

    // Check if the user is an admin
    // if(userDTO.getRole().equals("admin")){

    // Create the author
    // service.add(author);

    // Return a success message
    // return new ResponseEntity<>("Author created successfully.", HttpStatus.OK);
    // } else {

    // Return an error message
    // return new ResponseEntity<>("You are not an authorized to complete this
    // task.", HttpStatus.UNAUTHORIZED);
    // }
    // } catch (Exception e) {
    // return new ResponseEntity<>("Not yet implemented.",
    // HttpStatus.NOT_IMPLEMENTED);
    // }
    // }

    @ApiOperation(value = "Get an author by ID", notes = "Get an author by ID")
    @PostMapping(path = "/author/id")
    public @ResponseBody AuthorDTO getById(@RequestParam int id) {
        logger.info("Getting author by ID: " + id);

        // Return the author
        return service.getById(id);
    }

    @ApiOperation(value = "Get all authors", notes = "Get all authors")
    @PostMapping(path = "/author/all")
    public @ResponseBody Iterable<AuthorDTO> getAll() {
        logger.info("Getting all authors");

        // Return all authors
        return service.getAll();
    }

}
