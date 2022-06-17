package com.leafbound.test.services;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import com.leafbound.models.Product;
import com.leafbound.repositories.ProductRepository;
import com.leafbound.services.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {

	@Mock
	private static ProductRepository productRepo;

	@InjectMocks
	private static ProductServiceImpl productService;
	private static Product mockProduct1;
	private static Product mockProduct2;
	private static Product mockProduct3;
	private static Product mockProduct4;
	private static UUID uuid1;
	private static UUID uuid2;
	private static UUID uuid3;
	private static UUID uuid4;
	private static List<Product> dummyDb;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		productRepo = Mockito.mock(ProductRepository.class);
		productService = new ProductServiceImpl(productRepo);

		uuid1 = UUID.randomUUID();
		uuid2 = UUID.randomUUID();
		uuid3 = UUID.randomUUID();
		uuid4 = UUID.randomUUID();

		LocalDate localDate1 = LocalDate.now();
		LocalDate localDate2 = LocalDate.of(1992, Month.AUGUST, 29);

		mockProduct1 = new Product(uuid1, "Ralph Tresvant", "Penguin Books", "ISBN1", "Poetry", "Chillax Widdit",
				"Finnish", localDate1, "First Edition", "a psylocibin-induced foray into the poetry of New Edition",
				22.92);
		mockProduct2 = new Product(uuid2, "Charleston Heston", "Fredonia Works Limited", "ISBN2", "Self-Care",
				"Eyebrow Care for the Wayward Man", "Wolof", localDate2, "Third Reprint",
				"when general degeneracy and meticulous grooming merge", 8.52);

		dummyDb = new ArrayList<Product>();
		dummyDb.add(mockProduct1);
		dummyDb.add(mockProduct2);
	}

	@Test
	@Order(1)
	@DisplayName("1. Mock Validation Test")
	public void checkMockInjection() {
		assertThat(productRepo).isNotNull();
		assertThat(productService).isNotNull();
	}

	@Test
	@Order(2)
	@DisplayName("2. Passed Create Product Test")
	public void addProductTest_success() {
		mockProduct3 = mockProduct2;
		mockProduct3.setId(uuid3);

		when(productRepo.save(mockProduct3)).thenReturn(mockProduct3);

		assertEquals(mockProduct3, productService.createProduct(mockProduct3));
	}

	@Test
	@Order(3)
	@DisplayName("3. Failed Creation Product Test")
	public void addProductTest_failure() {
		mockProduct3 = new Product();
		mockProduct3.setId(uuid3);
		when(productRepo.save(mockProduct3)).thenReturn(mockProduct3);

		assertEquals(mockProduct4, productService.createProduct(mockProduct3));
	}

	@Test
	@Order(4)
	@DisplayName("4. Passed - Get Product based on ID Test")
	public void getProductByIDTest_success() {

		when(productRepo.getById(1)).thenReturn(mockProduct1);

		assertEquals(mockProduct1, productService.getProductById(1));
	}

	@Test
	@Order(5)
	@DisplayName("5. Failed - Get Product based on ID Test")
	public void getProductByIDTest_failure() {

		when(productRepo.getById(1)).thenReturn(mockProduct2);

		assertNotEquals(mockProduct1, productService.getProductById(1));
	}

	@Test
	@Order(5)
	@DisplayName("5. Get all Products - success")
	public void getAllProductTest_success() {
		when(productService.getAllProducts()).thenReturn(dummyDb);

		assertEquals(dummyDb, productService.getAllProducts());
	}

	@Test
	@Order(6)
	@DisplayName("6. Get all Products - failure")
	public void getAllProductTest_failure() {
		when(productService.getAllProducts()).thenReturn(null);

		assertNotEquals(dummyDb, productService.getAllProducts());
	}

	@Test
	@Order(7)
	@DisplayName("7. Update product - success")
	public void updateProduct_success() {
		mockProduct2.setIsbn("newIsbn");

		when(productService.getProductById(2)).thenReturn(mockProduct2);
		when(productRepo.save(mockProduct2)).thenReturn(mockProduct2);

		assertEquals("newIsbn", mockProduct2.getIsbn());
	}

	@Test
	@Order(8)
	@DisplayName("8. Update product - failure")
	public void updateProduct_failure() {
		mockProduct2.setIsbn("ISBN2");

		when(productService.getProductById(2)).thenReturn(mockProduct2);
		when(productRepo.save(mockProduct2)).thenReturn(mockProduct2);

		assertNull(productService.updateProduct(mockProduct2));
	}

	@Test
	@Order(9)
	@DisplayName("9. Delete Product Test - success")
	public void deleteProductById_success() {
		doNothing().when(productRepo).delete(mockProduct2);
		assertEquals(true, productService.deleteProduct(mockProduct2));
	}

	@Test
	@Order(10)
	@DisplayName("10. Delete Product Test - failure")
	public void deleteProductById_failure() {
		doNothing().when(productRepo).delete(mockProduct2);
		assertEquals(false, productService.deleteProduct(mockProduct2));
	}

}
