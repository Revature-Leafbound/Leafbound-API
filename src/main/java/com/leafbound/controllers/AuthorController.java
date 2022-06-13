package com.leafbound.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leafbound.models.AuthorDTO;
import com.leafbound.services.AuthorServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/api/v1")
@Api(value = "Author Collection", tags = "AUTHOR COLLECTION")
public class AuthorController {

    @Autowired
    private AuthorServiceImpl service;

    private static Logger logger = Logger.getLogger(AuthorController.class);

    @ApiOperation(value = "Create a new author", notes = "Add a new author to the DB")
    @PostMapping(path = "/author")
    public ResponseEntity<String> create(@RequestBody AuthorDTO author) {
        logger.info("Creating author: " + author);
        try {
            // Create the author
            service.add(author);

            // Return a success message
            return new ResponseEntity<>("Author created successfully.", HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exceptions
            return new ResponseEntity<>("Not yet implemented.", HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @ApiOperation(value = "Get an author by ID", notes = "Get an author by ID")
    @GetMapping(path = "/author/{id}")
    public @ResponseBody AuthorDTO readById(@PathVariable int id) {
        logger.info("Getting author by ID: " + id);

        // Return the author
        return service.getById(id);
    }

    @ApiOperation(value = "Get all authors", notes = "Get all authors")
    @GetMapping(path = "/authors")
    public @ResponseBody Iterable<AuthorDTO> readAll() {
        logger.info("Getting all authors");

        // Return all authors
        return service.getAll();
    }

    @ApiOperation(value = "Update author by id", notes = "Update author by provided IDit ")
    @PostMapping(path = "/author/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody AuthorDTO author) {
        logger.info("Updating author: " + author);
        try {

            // Update the author
            service.edit(id, author);

            // Return a success message
            return new ResponseEntity<>("Author updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exceptions
            return new ResponseEntity<>("Not yet implemented.",
                    HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @ApiOperation(value = "Delete author by ID", notes = "Delete an author")
    @DeleteMapping(path = "/author/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        logger.info("Deleting author with ID: " + id);
        try {

            // Delete the author
            service.remove(id);

            // Return a success message
            return new ResponseEntity<>("Author deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Not yet implemented.",
                    HttpStatus.NOT_IMPLEMENTED);
        }
    }

}
