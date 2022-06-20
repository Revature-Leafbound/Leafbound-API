package com.leafbound.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leafbound.models.Order;
import com.leafbound.models.OrderDetails;
import com.leafbound.models.OrderDetailsDTO;
import com.leafbound.models.Product;
import com.leafbound.repositories.OrderDetailsRepository;

@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private OrderDetailsRepository repository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Override
    public boolean add(OrderDetailsDTO orderDetailsDTO) {

        // Create a new OrderDetails object
        OrderDetails orderDetails = new OrderDetails();

        // Get the product
        Product product = productService.getProductById(orderDetailsDTO.getProductId());

        // Get the order
        Order order = orderService.getOrderById(orderDetailsDTO.getOrderId());

        // Set the order details
        orderDetails.setOrder(order);
        orderDetails.setProduct(product);
        orderDetails.setQuantity(orderDetailsDTO.getQuantity());

        // Save the order details
        int pk = repository.save(orderDetails).getId();

        // Return true if the primary key is not null
        return (pk > 0);
    }

    @Override
    public List<OrderDetailsDTO> getByOrderId(String orderId) {

        // Convert the string into a UUID
        UUID uuid = UUID.fromString(orderId);

        // Get the order details
        List<OrderDetails> orderDetails = repository.findByOrderId(uuid);

        // Create a new list of order details DTOs
        List<OrderDetailsDTO> orderDetailsDTOs = new ArrayList<>();

        // Loop through the order details
        for (OrderDetails orderDetail : orderDetails) {

            // Create a new order details DTO
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

            // Set the order details DTO
            orderDetailsDTO.setId(orderDetail.getId());
            orderDetailsDTO.setOrderId(orderDetail.getOrder().getId().toString());
            orderDetailsDTO.setProductId(orderDetail.getProduct().getId());
            orderDetailsDTO.setQuantity(orderDetail.getQuantity());
        }

        // Return the list of order details DTOs
        return orderDetailsDTOs;
    }

    @Override
    public boolean update(OrderDetailsDTO orderDetailsDTO) {

        // Get the order object
        Order order = orderService.getOrderById(orderDetailsDTO.getOrderId());

        // Get the product object
        Product product = productService.getProductById(orderDetailsDTO.getProductId());

        // Get the order details
        OrderDetails orderDetails = repository.findById(orderDetailsDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Order details not found"));

        // Set the order details
        orderDetails.setQuantity(orderDetailsDTO.getQuantity());
        orderDetails.setOrder(order);
        orderDetails.setProduct(product);

        // Save the order details
        int pk = repository.save(orderDetails).getId();

        // Return true if the primary key is not null
        return (pk > 0);
    }

    @Override
    public boolean delete(OrderDetailsDTO orderDetailsDTO) throws IllegalStateException {

        // Get the order details
        OrderDetails orderDetails = repository.findById(orderDetailsDTO.getId())
                .orElseThrow(() -> new IllegalStateException("OrderDetails not found"));

        // Delete the order details
        repository.delete(orderDetails);

        // Return true if the order details was deleted
        return (orderDetails.getId() > 0);
    }

}
