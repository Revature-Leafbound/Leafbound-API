package com.leafbound.test.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.leafbound.controllers.ProductController;
import com.leafbound.models.Product;
import com.leafbound.services.ProductService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerTest {
	
	private static Product mockProduct1;
	private static Product mockProduct2;
	private static UUID uuid1;
	private static UUID uuid2;
	private static UUID uuid3;
	private static UUID mockUUID1;
	private static Product mockProductCreation;
	private static Product mockProductModification;
	private static Product mockProductDeletion;
	private static List<Product> dummyDB;
	
	ObjectMapper om = new ObjectMapper()
			.registerModule(new JavaTimeModuel())
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	
	@Autowired
	ProductController productController;
	
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private ProductService pserv;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass() :: building test objects...");
		
		
        uuid1 = UUID.randomUUID();
        uuid2 = UUID.randomUUID();
        uuid3 = UUID.randomUUID();
        
		mockProduct1 = new Product(uuid1, "Autho Name 1", "Publisher's Name 1", "ISBN number 1", "Book Genre 1", "Book title 1", "Book Language 1", "1998-3-1", "Book Edition 1", 9.99);
		mockProduct2 = new Product(uuid2, "Autho Name 2", "Publisher's Name 2", "ISBN number 2", "Book Genre 2", "Book title 2", "Book Language 2", "1974-5-5", "Book Edition 2", 7.99);
		
		mockProductCreation = new Product(uuid3, "Autho Create 2", "Publisher's Create 2", "ISBN Create 2", "Create Genre 2", "Create title 2", "Create Language 2", "1974-5-5", "Book Edition 2", 75.99);

		mockProductModification = mockProductCreation;
		mockProductModification.setAuthor("Mod Author");
		mockProductModification.setPublisher("Mod publisher");
		mockProductModification.setIsbn("Mod isbn");
		mockProductModification.setGenre("Mod Genre");
		mockProductModification.setTitle("Mod Title");
		mockProductModification.setLanguage("Mod Language");
		mockProductModification.setPublished_date("2020-1-1");
		mockProductModification.setEdition("Mod Edition");
		mockProductModification.setDescription("Mod Description");
		mockProductModification.setList_price(88.88);
		
		mockProductDeletion = new Product(4, "Autho Name 4", "Publisher's Name 4", "ISBN number 4", "Book Genre 4", "Book title 4", "Book Language 4", "1944-4-4", "Book Edition 4", 47.99);
		
		dummyDb = new ArrayList<>();
		dummyDb.add(mockProduct1);
		dummyDb.add(mockProduct2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. AppContext Sanity Test")
	public void contextLoads() throws Exception {
		assertThat(productController).isNotNull();
		assertThat(pserv).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Get all products")
	public void getProducts_ShouldReturnAllProducts() throws Exception {
		when(pserv.getAll()).thenReturn(dummyDb);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/products");
		MvcResult result = mockmvc.perform(request).andReturn();
		
		assertEquals(om.writeValueAsString(dummyDb), result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Attemp to pull invalid product")
	public void getProduct_ShouldReturnInvalid() throws Exception {
		when(pserv.getById(mockUUID1.toString())).thenReturn(mockProduct1);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/product?id=1");
		MvcResult result = mockmvc.perform(request).andReturn();
		
		assertEquals(om.writeValueAsString(mockProduct1), result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Attempt to pull valid user")
	public void getProduct_ShouldReturnUser() throws Exception {
		when(pserv.getById(mockUUID1.toString())).thenReturn(mockProduct1);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/v1/product?id=" + mockUUID1);
		MvcResult result = mockmvc.perform(request).andReturn();
		
		assertEquals(om.writeValueAsString(mockProduct1), result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(5)
	@DisplayName("5. Create a new product")
	public void postProduct_ShouldReturnSuccess() throws Exception {
		when(pserv.add(mockProductCreation)).thenReturn(true);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/api/v1/product?id=8e4ac3a8-ae4a-4ea1-85a8-9d9d1bff8f60")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockProductCreation))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		
		assertEquals(om.writeValueAsString(ClientMessageUtil.CREATION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(6)
	@DisplayName("6. Create a new product - failed")
	public void postProduct_ShouldReturnFailed() throws Exception {
		
		when(pserv.add(mockProductCreation)).thenReturn(true);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/api/v1/product?id=8e4ac3a8-ae4a-4ea1-85a8-9d9d1bff8f60")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockProductCreation))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		
		assertEquals(om.writeValueAsString(ClientMessageUtil.CREATION_FAILED),
				result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(7)
	@DisplayName("7. Update a product")
	public void postUpdateProduct_ShouldReturnTrue() throws Exception {
		when(pserv.edit(mockUserModification)).thenReturn(true);
		
		RequestBuilder request = MockMvcRequestBuilders
				.put("/api/v1/product?id=8e4ac3a8-ae4a-4ea1-85a8-9d9d1bff8f60")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockProductModification))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		
		assertEquals(om.writeValueAsString(ClientMessageUtil.UPDATE_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(8)
	@DisplayName("8. Update a product - failed")
	public void postUpdateProduct_ShouldReturnFaled() throws Exception {
		
		when(pserv.edit(mockProductModification)).thenReturn(true);
		
		RequestBuilder request = MockMvcRequestBuilders
				.put("/api/v1/product?id=8e4ac3a8-ae4a-4ea1-85a8-9d9d1bff8f60")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockProductModification))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		
		assertEquals(om.writeValueAsString(ClientMessageUtil.UPDATE_FAILED),
				result.getResponse().getContentAsString());
		
	}
	
	@Test
	@Order(9)
	@DisplayName("9. Delete product")
	public void testDeleteProduct() throws Exception {
		
		when(pserv.remove(mockProduct1.getId().toString())).thenReturn(true);
		
		RequestBuilder request = MockMvcRequestBuilders
				.delete("/api/v1/product?id=8e4ac3a8-ae4a-4ea1-85a8-9d9d1bff8f60")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockProductDeletion))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		
		assertEquals(om.writeValueAsString(ClientMessageUtil.DELETION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(10)
	@DisplayName("10. Delete user - fail")
	public void testDeleteProductFail() throws Exception {
		
		when(pserv.remove(mockProduct1.getId().toString())).thenReturn(true);
		
		RequestBuilder request = MockMvcRequestBuilders
				.delete("/api/v1/user?id=8e4ac3a8-ae4a-4ea1-85a8-9d9d1bff8f60")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockUserDeletion))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		
		assertEquals(om.writeValueAsString(ClientMessageUtil.DELETE_FAILED),
				result.getResponse().getContentAsString());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
