package com.leafbound.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.leafbound.models.Cart;
import com.leafbound.models.Product;
import com.leafbound.models.User;
import com.leafbound.repositories.CartRepository;
import com.leafbound.services.CartService;
import com.leafbound.services.CartServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CartServiceTest {

    @Mock
    private static CartRepository cartRepo;

    @InjectMocks
    private static CartServiceImpl cartService;
	private static Cart mockCart1, mockCart2, mockCart3;
    private static Product mockProd1, mockProd2, mockProd3;
    private static User mockUser1, mockUser2, mockUser3;
	private static List<Cart> dummyDB;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        cartRepo = Mockito.mock(CartRepository.class);
        cartService = new CartServiceImpl(cartRepo);

        Random rand = new Random();
        int maxNumber = 100_000_000;
        int id1 = rand.nextInt(maxNumber) + 1;
		int id2 = rand.nextInt(maxNumber) + 1;
        int id3 = rand.nextInt(maxNumber) + 1;
        int prodId1 = rand.nextInt(maxNumber) + 1;
		int prodId2 = rand.nextInt(maxNumber) + 1; 
        int custId1 = rand.nextInt(maxNumber) + 1;
		int custId2 = rand.nextInt(maxNumber) + 1;        

		mockCart1 = new Cart(id1, mockProd1, 3, mockUser1);
		mockCart2 = new Cart(id2, mockProd2, 20, mockUser2);
        mockCart3 = new Cart(id3, mockProd3, 27, mockUser3);

		mockCartDeletion = new Cart(567, mockProd3, 5, mockUser3);

		dummyDB = new ArrayList<>();
		dummyDB.add(mockCart1);
		dummyDB.add(mockCart2);
    }

    @Test
    @Order(1)
    @DisplayName("1. Mock Validation Test")
    public void checkMockInjection() {
        assertThat(cartRepo).isNotNull();
        assertThat(cartService).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("2. Create Cart Test - success")
    public void addCartTest_success() {
        mockCart3 = mockCart2;
        mockCart3.setId(3);

        when(cartRepo.save(mockCart3)).thenReturn(mockCart3);

        assertEquals(true, cartService.addtoCart(mockCart3));
    }

    @Test
    @Order(3)
    @DisplayName("3. Create Cart Test - failure")
    public void addCartTest_failure() {
        mockCart3 = new Cart();
        mockCart3.setId(3);

        when(cartRepo.save(mockCart3)).thenReturn(mockCart3);

        assertEquals(false, cartService.addtoCart(mockCart3));
    }







    @Test
    @Order(4)
    @DisplayName("4. Delete Cart Test - success")
    public void deleteCartById_success() {
        when(cartRepo.deleteById(999)).thenReturn(true);
        assertEquals(true, cartService.remove(mockCart2.getById(999)));
    }

    @Test
    @Order(5)
    @DisplayName("5. Delete Cart Test - failure")
    public void deleteCartById_failure() {
        when(cartRepo.deleteById(999)).thenReturn(false);
        assertNotNull(cartService.getById(999));
    }




    
}
