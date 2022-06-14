package com.leafbound.test.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.leafbound.models.Product;
import com.leafbound.repositories.ProductRepository;



@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {
    
    @Mock
    private static ProductRepository productRepo;

    @InjectMocks
    private static ProductServiceImpl productService;
    private static Product mockProduct1, mockProduct2, mockProduct3;
    private static List<Product> dummyDb;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        productRepo = Mockito.mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepo);

        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(1992, Month.AUGUST, 29);

        mockProduct1 = new Product(1, "Ralph Tresvant", "Penguin Books", "ISBN1", "Poetry", "Chillax Widdit", "Finnish",
        localDate1, "First Edition", "a psylocibin-induced foray into the poetry of New Edition", 22.92);
        mockProduct2 = new Product(2, "Charleston Heston", "Fredonia Works Limited", "ISBN2", "Self-Care", "Eyebrow Care for the Wayward Man", "Wolof",
        localDate2, "Third Reprint", "when general degeneracy and meticulous grooming merge", 8.52);

        dummyDb = new ArrayList<Product>();
        dummyDb.add(mockProduct1);
        dummyDb.add(mockProduct2);
    }

    @Test
    @Order(1)
    @DisplayName("1. Mock Validation Test")
    public void checkMockInjection(){
        assertThat(productRepo).isNotNull();
        assertThat(productService).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("2. Create Product Test")
    public void createProductTest_success(){
        mockProduct3 = mockProduct2;
        mockProduct3.setid(3);

        when(productRepo.save(mockProduct3)).thenReturn(mockProduct3);

        assertEquals(true, productService.createUser(mockProduct3));
    }

    




}
