package com.leafbound.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
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

import com.leafbound.models.UserRole;
import com.leafbound.services.UserRoleService;
import com.leafbound.services.UserRoleServiceImpl;

import io.jsonwebtoken.io.IOException;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserRoleController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRoleControllerTest {
	
	private static UserRole mockUserRole1;
	private static UserRole mockUserRole2;
	
	private static List<UserRole> dummyDb;

	ObjectMapper om = new ObjectMapper();
	
	@Autowired
	UserRoleController userController;

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private UserRoleServiceImpl service;

	@SuppressWarnings("deprecation")
	public boolean isValidJSON(final String json) throws Exception {
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
	@Before
	static void setUpBeforeClass() throws Exception {
		mockUserRole1 = new UserRole(1, "manager", "manager1");

		mockUserRole2 = new UserRole();
		mockUserRole2.setId(2);
		mockUserRole2.setName("Customer");
		mockUserRole2.setDescription("Customer1");

		dummyDb = new ArrayList<UserRole>();
		dummyDb.add(mockUserRole1);
		dummyDb.add(mockUserRole2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. AppContext Sanity Test")
	public void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}


}



























