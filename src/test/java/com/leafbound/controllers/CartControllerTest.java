package com.leafbound.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import com.leafbound.util.ClientMessageUtil;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.leafbound.models.Cart;
import com.leafbound.models.Product;
import com.leafbound.models.User;
import com.leafbound.services.CartService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CartController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartControllerTest {

	private static Cart mockCart1, mockCart2;
    private static Product mockProd1, mockProd2, mockProd3;
    private static User mockUser1, mockUser2, mockUser3;
	private static Cart mockCartCreation;
	private static Cart mockCartDeletion;
	private static List<Cart> dummyDB;

	ObjectMapper om = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

	@Autowired
	CartController cartController;

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private CartService cserv;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass() :: building test objects...");

        Random rand = new Random();
        int maxNumber = 100_000_000;
        int id1 = rand.nextInt(maxNumber) + 1;
		int id2 = rand.nextInt(maxNumber) + 1;     

		mockCart1 = new Cart(id1, mockProd1, 3, mockUser1);
		mockCart2 = new Cart(id2, mockProd2, 20, mockUser2);

		mockCartDeletion = new Cart(567, mockProd3, 5, mockUser3);

		dummyDB = new ArrayList<>();
		dummyDB.add(mockCart1);
		dummyDB.add(mockCart2);
	}

	@Test
	@Order(1)
	@DisplayName("1. AppContext Sanity Test")
	void contextLoads() throws Exception {
		assertThat(cartController).isNotNull();
		assertThat(cserv).isNotNull();
	}

	@Test
	@Order(2)
	@DisplayName("2. Create a new cart")
	void postCart_ShouldReturnSuccess() throws Exception {
		when(cserv.addtoCart(mockCartCreation)).thenReturn(true);

		RequestBuilder request = MockMvcRequestBuilders
				.post("/api/v1/cart?id=8_500_001")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockCartCreation))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();

		assertEquals(om.writeValueAsString(ClientMessageUtil.CREATION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}

	@Test
	@Order(3)
	@DisplayName("3. Create a new cart - failed")
	void postCart_ShouldReturnFailed() throws Exception {

		when(cserv.addtoCart(mockCartCreation)).thenReturn(true);

		RequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/cart?id=9_500_001")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockCartCreation))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();

		assertEquals(om.writeValueAsString(ClientMessageUtil.CREATION_FAILED),
				result.getResponse().getContentAsString());
	}


	@Test
	@Order(4)
	@DisplayName("4. Delete cart")
	void testDeleteCart() throws Exception {

		when(cserv.deleteCart(8_500_001)).thenReturn(true);

		RequestBuilder request = MockMvcRequestBuilders
				.delete("/api/v1/cart?id=8_500_001")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockCartDeletion))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();

		assertEquals(om.writeValueAsString(ClientMessageUtil.DELETION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}

	@Test
	@Order(5)
	@DisplayName("5. Delete cart - fail")
	void testDeleteCartFail() throws Exception {

		when(cserv.deleteCart(8_500_001)).thenReturn(true);

		RequestBuilder request = MockMvcRequestBuilders
				.delete("/api/v1/cart?id=8_500_001")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockCartDeletion))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();

		assertEquals(om.writeValueAsString(ClientMessageUtil.DELETION_FAILED),
				result.getResponse().getContentAsString());
	}
    
}
