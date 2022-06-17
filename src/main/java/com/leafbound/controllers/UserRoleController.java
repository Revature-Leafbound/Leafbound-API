package com.leafbound.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leafbound.models.UserRole;
import com.leafbound.services.UserRoleServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
@Api(value = "UserRoleController", tags = "USER ROLE COLLECTIONS")
public class UserRoleController {

    private static Logger log = Logger.getLogger(UserRoleController.class);

    @Autowired
    private UserRoleServiceImpl service;

    @GetMapping("/role/{id}")
    @ApiOperation(value = "Find user role by id number", response = UserRole.class)
    public @ResponseBody UserRole readById(@PathVariable int id) {
        log.info("Getting user role with id: " + id);
        return service.getById(id);
    }

    @PostMapping("/role")
    @ApiOperation(value = "Create new user role entity")
    public @ResponseBody String createUserRole(@RequestBody UserRole userRole) {
        log.info("Creating user role");
        return (service.add(userRole)) ? "User Role created successfullly." : "Error creating user role.";
    }

    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "Remove user role entity by ID.")
    public @ResponseBody String deleteUserRole(@PathVariable int id) {
        log.info("Deleting user role");
        return (service.remove(id)) ? "Delete successful" : "Delete failed";
    }
}
