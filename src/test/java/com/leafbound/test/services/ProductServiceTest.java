package com.leafbound.test.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {
	
	@Mock
	private static Productrepository prepo;
	
	@InjectMocks
	private static ProductServiceImpl pserv;
	
	private static UUID uuid1;
	private static UUID uuid2;
	private static UUID uuid3;
	private static Product p1;
	private static Product p2;
	private static Product p3;
	private static List<Product> dummyDb;
	
	@BeforeAll
	static void setupBeforeCall() throws Exception {
		prepo = Mockito.mock(ProductRepository.class);
		pserv = new ProductServiceImpl(prepo);
		
		uuid1 = UUID.randomUUID();
		uuid2 = UUID.randomUUID();
		uuid3 = UUID.randomUUID();
		
		p1 = new Product(uuid1, "Autho Name 1", "Publisher's Name 1", "ISBN number 1", "Book Genre 1", "Book title 1", "Book Language 1", "1998-3-1", "Book Edition 1", 9.99);
		p2 = new Product(uuid2, "Autho Name 2", "Publisher's Name 2", "ISBN number 2", "Book Genre 2", "Book title 2", "Book Language 2", "1974-5-5", "Book Edition 2", 7.99);
		
		dummyDb = new ArrayList<Product>();
		dummyDb.add(p1);
		dummyDb.add(p2);
		
	}
	
	
	@Test
	@Order(1)
	@DisplayName("1.  Mock Validation Sanity Test")
	void checkMockInjection() {
		assertThat(prepo).isNotNUll();
		assertThat(pserv).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Register a new Product Test - Pass")
	public void createProductTest_pass() {
		// Arrange
		p3 = p2;
		p3.setId(uuid3);
		
		// Action
		when(prepo.save(p3)).thenReturn(p3);
		
		// Assert
		assertEquals(true, pserv.add(p3));
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Register a new Product Test - Fail")
	public void createProductTest_fail() {
		// Arrange
		p3 = p2;
		p3.setId(uuid3);
		
		// Action
		when(prepo.save(p3)).thenReturn(p3);
		
		// Assert
		assertEquals(true, pserv.add(p3));
	}
	
	@Test
	@Order(4)
	@DisplayName("4.  Get Product by Id Test - Pass")
	public void getByIdTest_pass() {
		// Arrange
		// Action
		when(pserv.getProductById(uuid1)).thenReturn(p1);
		
		// Assert
		assertEquals(p1, pserv.getProductByid(uuid1));
	}
	
	@Test
	@Order(5)
	@DisplayName("5.  Get all Products Test - Pass")
	public void getAllProducts_pass() {
		// Arrange
		// Action
		when(pserv.getAll()).thenReturn(dummyDb);
		
		// Assert
		assertEquals(dummyDb, pserv.getAll());
	}
	
	@Test
	@Order(6)
	@DisplayName("6.  Update Product Test - Pass")
	public void updateTest_pass() {
		p2.setAuthor("Bobby");
		
		when(pserv.getUserById(uuid2)).thenReturn(p2);
		when(prepo.save(p2)).thenReturn(p2);
		
		assertEquals(true, pserv.updateProduct(p2));
	}
	
	@Test
	@Order(7)
	@DisplayName("7.  Delete Product Test - Pass")
	public void deleteTest_pass() {
		doNothing().when(prepo).delete(p2);
		assertEquals(true, pserv.deleteUser(p2));
	}
}
