package com.leafbound.controllers;

import static org.assertj.core.api.Assertions.assertThat;


import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leafbound.models.Order;
import com.leafbound.services.OrderService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderControllerTest {
	
	private static Order mockOrder1;
	private static Order mockOrder2;
	private static Order mockOrderCreation;
	private static Order mockOrderModification;
	private static Order mockOrderDeletion;
	private static List<Order> dummyDb;
	
	ObjectMapper om = new ObjectMapper();
	
	@Autowired
	OrderController orderController;

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private OrderService service;
	
	@SuppressWarnings("deprecation")
	public boolean isValidJSON(final String json) {
		boolean valid = false;
		try {
			final JsonParser parser = new ObjectMapper().getFactory().createParser(json);
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
	
	@Test
	@DisplayName("1. AppContext Sanity Test")
	public void contextLoads() throws Exception {
		assertThat(orderController).isNotNull();
	}

}
