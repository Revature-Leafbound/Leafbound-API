package com.leafbound.services;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.leafbound.models.Order;
import com.leafbound.models.OrderDetails;
import com.leafbound.models.OrderDetailsDTO;
import com.leafbound.models.Product;
import com.leafbound.models.User;
import com.leafbound.repositories.OrderDetailsRepository;
import com.leafbound.repositories.OrderRepository;
import com.leafbound.repositories.ProductRepository;
import com.leafbound.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailsServiceTest {

    @TestConfiguration
    static class OrderDetailsServiceImplTestContextConfiguration {

        @Bean
        public OrderDetailsService service() {
            return new OrderDetailsServiceImpl();
        }
    }

    @Autowired
    private OrderDetailsServiceImpl service;

    @MockBean
    private OrderDetailsRepository repository;

    @MockBean
    private ProductRepository productRepo;

    @MockBean
    private OrderRepository orderRepo;

    @MockBean
    private UserRepository userRepo;

    private OrderDetails orderDetails;

    private Order order;

    // Order UUID
    private UUID orderUUID = UUID.fromString("4d302845-9ed7-41cd-84b0-67f2f90633f1");

    @Before
    public void setup() {

        // Create a new User object
        User user = new User();

        // Create a new Order object
        order = new Order(orderUUID, user, LocalDate.now());

        // Create a Product Object
        Product product = new Product(1, "Ralph Tresvant", "Penguin Books", "ISBN1", "Poetry", "Chillax Widdit",
                "Finnish", LocalDate.of(2022, 01, 05), "First Edition",
                "a psylocibin-induced foray into the poetry of New Edition", 22.92);

        // Create a new OrderDetails object
        orderDetails = new OrderDetails(product, 1, order);

        Mockito.when(repository.save(orderDetails)).thenReturn(orderDetails);
    }

    @Test
    public void testAdd() {
        OrderDetailsDTO orderDTO = new OrderDetailsDTO();
        orderDTO.setOrderId(orderUUID.toString());
        orderDTO.setProductId(1);
        orderDTO.setQuantity(1);

        // Action
        service.add(orderDTO);

        // Assert
        Mockito.verify(repository).save(orderDetails);
    }

}
