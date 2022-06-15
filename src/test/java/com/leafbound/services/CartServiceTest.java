package com.leafbound.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.leafbound.models.Carts;
import com.leafbound.models.User;
import com.leafbound.repositories.CartRepository;
import com.leafbound.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartServiceTest {

	// cart
	@Mock
	private static CartRepository crepo;

	@InjectMocks
	private static CartServiceImpl cserv;

	private static Carts c1, c2;
	static List<Carts> dummyCart;

	// user
	@Mock
	private static UserRepository urepo;

	@InjectMocks
	private static UserServiceImpl userv;

	private static User u1, u2;
	static List<User> dummyUser;

	// products
	@Mock
	private static ProductRepository prepo;

	@InjectMocks
	private static ProductServiceImpl pserv;

	private static Product p1, p2;
	static List<Product> dummyProduct;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		LocalDate localDate1 = LocalDate.now();
		LocalDate localDate2 = LocalDate.of(2020, Month.MARCH, 17);
		
		//cart
		//id product quantity user
		
		crepo = Mockito.mock(CartRepository.class);
		cserv = new CartServiceImpl(crepo);
		
		c1 = new Carts(1, new Product(), 1, new User());
		c2 = new Carts(2, new Product(), 1, new User());
		
		dummyCart = new ArrayList<Carts>();
		dummyCart.add(c1);
		dummyCart.add(c2);
		
		//user
		//id firstName, lastName, password, email, roleId, settingsId
		urepo = Mockito.mock(UserRepository.class);
		userv = new UserServiceImpl(urepo);
		
		u1 = new User();
		u2 = new User();
		
		dummyUser = new ArrayList<User>();
		dummyUser.add(u1);
		dummyUser.add(u2);
		
		//products
		//
	      prepo = Mockito.mock(ProductRepository.class);
	      pserv = new ProductServiceImpl(prepo);

	     p1 = new Product(1, "Albert Camus", "Vintage", "ISBN1", "Philosophy", "The Myth of Sisyphus", "French",
	        localDate1, "First Edition", "Core book of Absuridism", 14.99);
	     p2 = new Product(2, "Heinrich Heine", "Hoffman und Campe", "ISBN2", "Poetry", "Buch Der Lieder", "German",
	        localDate2, "Collection", "Die Leiden Des Jugends", 8.52);

	     dummyProduct = new ArrayList<Product>();
	     dummyProduct.add(p1);
	     dummyProduct.add(p2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. Mock Validation Sanity Test")
	void checkMockInjection() {
		assertThat(crepo).isNotNull();
		assertThat(cserv).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Add to cart")
	void testAddCart() {
		Carts c3 = new Carts(new Product(), 1, new User());
		c3.setId(3);
		when(crepo.save(c3)).thenReturn(c3);
		assertEquals(true, cserv.addtoCart(c3));
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Delete cart")
	void testDeleteCart {
		doNothing().when(crepo).delete(c2);
		assertEquals(true, cserv.deleteCart(2));
	}
}
