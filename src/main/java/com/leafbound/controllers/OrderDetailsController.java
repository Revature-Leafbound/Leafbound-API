package com.leafbound.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leafbound.models.OrderDetailsDTO;
import com.leafbound.services.OrderDetailsServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "OrderDetailsRestController", tags = "ORDER DETAILS COLLECTION")
public class OrderDetailsController {

    private static Logger logger = Logger.getLogger(OrderDetailsController.class);

    @Autowired
    private OrderDetailsServiceImpl service;

    @PostMapping(path = "/orderdetails")
    @ApiOperation(value = "Adding order details")
    public @ResponseBody boolean createOrderDetails(@RequestBody OrderDetailsDTO orderDetailsDTO) {
        logger.info("creating order details in controller...");
        return service.add(orderDetailsDTO);
    }

    @GetMapping(path = "/orderdetails/{orderId}")
    @ApiOperation(value = "Getting order details by Id")
    public @ResponseBody List<OrderDetailsDTO> getById(@PathVariable String orderId) {
        logger.info("finding order details by Id in controller...");
        return service.getByOrderId(orderId);
    }

}
