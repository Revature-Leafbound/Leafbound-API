package com.leafbound.controllers;

import static com.leafbound.util.ClientMessageUtil.CREATION_FAILED;
import static com.leafbound.util.ClientMessageUtil.CREATION_SUCCESSFUL;
import static com.leafbound.util.ClientMessageUtil.DELETION_FAILED;
import static com.leafbound.util.ClientMessageUtil.DELETION_SUCCESSFUL;
import static com.leafbound.util.ClientMessageUtil.UPDATE_FAILED;
import static com.leafbound.util.ClientMessageUtil.UPDATE_SUCCESSFUL;

import java.util.List;

import com.leafbound.models.ClientMessage;
import com.leafbound.models.Carts;
import com.leafbound.services.CartServiceImpl;

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

@RestController
@RequestMapping("/api/v1")
@Api(value = "Carts", description = "REST controller related to Carts Entities")
public class CartController {
    
    @Autowired
    private CartServiceImpl cserv;

    @GetMapping(path = "/cart")
    @ApiOperation(value = "Find cart by id number", notes = "Provide an id to lookup a specific cart in the API", response = Carts.class)
    public @ResponseBody Carts getById(@RequestParam(value = "id") int id) {
        return cserv.findById(id);
    }

//  POSSIBLE FIX FOR SONARCLOUD ISSUE    
    @PostMapping(path = "/cart")
    @ApiOperation(value = "Create new cart entity", notes = "Add a new cart in the API.")
    public @ResponseBody ClientMessage create(@RequestBody CartsRequestModel cartRequestModel) {
        Carts cart = new Carts(cartRequestModel);
        return cserv.addtoCart(cart) ? CREATION_SUCCESSFUL : CREATION_FAILED;
    }

    @DeleteMapping(path = "/cart")
    @ApiOperation(value = "Remove cart entity by ID.", notes = "Provide an id to delete a specific cart in the API.")
    public @ResponseBody ClientMessage delete(@RequestParam(value = "id") int id) {
        return cserv.deleteCart(id) ? DELETION_SUCCESSFUL : DELETION_FAILED;
    }	

}
