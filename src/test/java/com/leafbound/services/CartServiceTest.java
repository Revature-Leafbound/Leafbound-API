package com.leafbound.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
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
	private static CartRepository crepo;
	
	@InjectMocks
	private static CartServiceImpl cserv;
	
	private static Cart c1, c2;
	private static User u1, u2, u3;
	private static Product p1, p2, p3;
	static List<Cart> dummyDb;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		crepo = Mockito.mock(CartRepository.class);
		cserv = new CartServiceImpl(crepo);
		
		c1 = new Cart(1, p1, 1, u1);
		c2 = new Cart(2, p2, 2, u2);
		
		dummyDb = new ArrayList<Cart>();
		dummyDb.add(c1);
		dummyDb.add(c2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. Mock Validation Sanity Test")
	public void checkMockInjection() {
		assertThat(crepo).isNotNull();
		assertThat(cserv).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Add to Cart - Success")
	public void testAddCart_Success() throws Exception {
		Cart c3 = new Cart(p3, 3, u3);
		c3.setId(3);
		when(crepo.save(c3)).thenReturn(c3);
		assertEquals(true, cserv.addtoCart(c3));
	}
	
	@Order(3)
	@DisplayName("3. Add to Cart - Failure")
	public void testAddCart_Failure() throws Exception {
		Cart c3 = new Cart(p3, 3, u3);
		c3.setId(3);
		when(crepo.save(c3)).thenReturn(c3);
		assertNotEquals(false, cserv.addtoCart(c3));
	}
	
//	@Order(4)
//	@DisplayName("4. Delete Cart - Success")
//	public void testDeleteCart_Success() throws Exception {
//		when(crepo.delete(c2)).thenReturn(2);
//		assertEquals(true, cserv.deleteCart(2));
//	}
	
}
