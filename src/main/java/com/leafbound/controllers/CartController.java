package com.leafbound.controllers;

import static com.leafbound.util.ClientMessageUtil.CREATION_FAILED;
import static com.leafbound.util.ClientMessageUtil.CREATION_SUCCESSFUL;
import static com.leafbound.util.ClientMessageUtil.DELETION_FAILED;
import static com.leafbound.util.ClientMessageUtil.DELETION_SUCCESSFUL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leafbound.models.Cart;
import com.leafbound.models.ClientMessage;
import com.leafbound.services.CartServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Cart", tags = "CART COLLECTION")
public class CartController {

    @Autowired
    private CartServiceImpl cserv;

    @PostMapping(path = "/cart")
    @ApiOperation(value = "Create new cart entity", notes = "Add a new cart in the API.")
    public @ResponseBody ClientMessage create(@RequestBody Cart cart) {
        // Cart cart = new Cart(cartRequestModel);
        return cserv.addtoCart(cart) ? CREATION_SUCCESSFUL : CREATION_FAILED;
    }

    @DeleteMapping(path = "/cart/{id}")
    @ApiOperation(value = "Remove cart entity by ID.", notes = "Provide an id to delete a specific cart in the API.")
    public @ResponseBody ClientMessage delete(@PathVariable int id) {
        return cserv.deleteCart(id) ? DELETION_SUCCESSFUL : DELETION_FAILED;
    }

}
