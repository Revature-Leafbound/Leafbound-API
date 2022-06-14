package com.leafbound.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
import com.leafbound.services.ProductService;
import com.leafbound.services.ProductServiceImpl;





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
    @DisplayName("2. Passed Create Product Test")
    public void addProductTest_success(){
        mockProduct3 = mockProduct2;
        mockProduct3.setid(3);

        when(productRepo.save(mockProduct3)).thenReturn(mockProduct3);

        assertEquals(true, productService.add(mockProduct3));
    }

    @Test 
    @Order(3)
    @DisplayName("3. Failed Creation UserRole Test")
    public void addProductTest_failure(){
        mockProduct3 = new Product();
        mockProduct3.setid(3);
        when(productRepo.save(mockProduct3)).thenReturn(mockProduct3);

        assertEquals(false, productService.add(mockProduct3));
    }

    @Test
    @Order(4)
    @DisplayName("4. Passed - Get Product based on ID Test")
    public void getProductByIDTest_success(){

        when(productRepo.getProductById(1)).thenReturn(mockProduct1);

        assertEquals(mockProduct1, productService.getProductById(1));
    }

    @Test
    @Order(5)
    @DisplayName("5. Failed - Get Product based on ID Test")
    public void getProductByIDTest_failure(){

        when(productRepo.getProductById(1)).thenReturn(mockProduct2);

        assertNotEquals(mockProduct1, productService.getProductById(1));
    }

    @Test
    @Order(5)
    @DisplayName("5. Get all Products - success")
    public void getAllProductTest_success(){
        when(productService.getAll()).thenReturn(dummyDb);

        assertEquals(dummyDb, productService.getAll());
    }

    @Test
    @Order(6)
    @DisplayName("6. Get all Products - failure")
    public void getAllProductTest_failure(){
        when(productService.getAll()).thenReturn(null);

        assertNotEquals(dummyDb, productService.getAll());
    }

    @Test
    @Order(7)
    @DisplayName("7. Update product - success") 
        public void updateProduct_success(){
            mockProduct2.setIsbn("newIsbn");

            when(productService.getById(2)).thenReturn(mockProduct2);
            when(productRepo.save(mockProduct2)).thenReturn(mockProduct2);

            assertEquals(true, productService.update(mockProduct2));
        }
    


    @Test
    @Order(8)
    @DisplayName("8. Update product - failure") 
        public void updateProduct_failure(){
            mockProduct2.setIsbn("ISBN2");

            when(productService.getById(2)).thenReturn(mockProduct2);
            when(productRepo.save(mockProduct2)).thenReturn(mockProduct2);

            assertFalse(productService.update(mockProduct2));
        }
    

    
    

    @Test
    @Order(9)
    @DisplayName("9. Delete Product Test - success") 
        public void deleteProductById_success(){
            when(productRepo.delete(2)).thenReturn(true);
            assertEquals(true, productService.remove(mockProduct2.getById(2)));
        }
    

    @Test
    @Order(10)
    @DisplayName("10. Delete Product Test - failure") 
        public void deleteProductById_failure(){
            when(productRepo.delete(2)).thenReturn(false);
            assertNotNull(productService.getById(2));
        }
    


    




}
