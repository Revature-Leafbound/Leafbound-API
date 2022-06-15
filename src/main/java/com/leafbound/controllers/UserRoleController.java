package com.leafbound.controllers;

import java.util.List;

import com.leafbound.models.UserRole;
import com.leafbound.services.UserRoleServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
@Api(value = "UserRoleController", tags = "USER ROLE COLLECTIONS")
public class UserRoleController {

    private static Logger log = Logger.getLogger(UserController.class);

    @Autowired
	private UserRoleServiceImpl service;

    @GetMapping("/roles")
    @ApiOperation(value = "Find all user roles.", response = UserRole.class)
	public @ResponseBody List<UserRole> readAllUserRoles() {
		log.info("Getting all user roles");
		return service.getAll();
	}

    @GetMapping("/role")
    @ApiOperation(value = "Find user role by id number", response = UserRole.class)
    public @ResponseBody UserRole readById(@RequestBody int id) {
        log.info("Getting user role with id: " + id);
        return service.getById(id);
    }

    @PostMapping("/role")
    @ApiOperation(value = "Create new user role entity")
    public @ResponseBody String createUserRole(@RequestBody UserRole userRole) {
        log.info("Creating user role");
        return (service.add(userRole)) ? "User Role created successfullly." : "Error creating user role.";
    }

    // @PatchMapping("/role")
    // @ApiOperation(value = "Update user role entity by id.")
    // public @ResponseBody UserRole updateUserRole(@RequestBody int id, UserRole userRole) {
    //     return service.edit(id, userRole) ? "User role updated successfully" : "User role update failed";
    // }

    @DeleteMapping("/role")
    @ApiOperation(value = "Remove user role entity by ID.")
    public @ResponseBody String deleteUserRole(@RequestBody int id) {
        log.info("Deleting user role");
        return (service.remove(id)) ? "Delete successful" : "Delete failed";
    }
}

