package com.leafbound.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leafbound.models.Carts;
import com.leafbound.models.User;
import com.leafbound.services.CartService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CartController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartControllerTest {
	
	private static Carts mockCart1;
	private static Carts mockCart2;
	private static Carts mockCartAddition;
	private static Carts mockCartDeletion;
	private static List<Carts> dummyDb;
	
	ObjectMapper om = new ObjectMapper();
	
	@Autowired
	CartController cartController;
	
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private CartService cservice;
	
	@SuppressWarnings("deprecation")
	public boolean isValidJSON (final String json) {
		boolean valid = false;
		try {
			final JsonParser parser = new ObjectMapper().getJsonFactory().createJsonParser(json);
			while (parser.nextToken() != null) {
			}
			valid = true;
		} catch (JsonParseException jpe) {
			jpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return valid;
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mockCart1 =  new Carts(1, new Product(), 1, new User());
		mockCart2 =  new Carts(2, new Product(), 1, new User());
		
		
		
		mockCartDeletion = new Carts(4, new Product(), 3, new User());
		
		dummyDb = new ArrayList<Carts>();
		dummyDb.add(mockCart1);
		dummyDb.add(mockCart2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. AppContext Sanity Test")
	public void contestLoads() throws Exception {
		assertThat(cartController).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Add to Cart Happy Path Scenario Test")
	public void testAddCart() throws Exception {
		mockCartAddition.setId(3);
		when(cservice.addtoCart(mockCartAddition)).thenReturn(true);
		
		RequestBuilder request =  MockMvcRequestBuilders.post("/api/v1/cart")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockCartAddition))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.CREATION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Delete Cart Happy Path Scenario Test")
	public void testDeleteCart() throws Exception {
		when(cservice.deleteCart(1)).thenReturn(mockCart2);
		RequestBuilder request =  MockMvcRequestBuilders.delete("/api/v1/cart")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockCartAddition))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.DELETION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
}
