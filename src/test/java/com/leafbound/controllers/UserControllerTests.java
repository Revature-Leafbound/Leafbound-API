package com.leafbound.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leafbound.models.User;
import com.leafbound.models.UserDTO;
import com.leafbound.models.UserRole;
import com.leafbound.repositories.UserRepository;
import com.leafbound.repositories.UserRoleRepository;
import com.leafbound.services.UserServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTests {

	private static User mockUser1;
	private static User mockUser2;
	private static User mockUserCreation;
	private static User mockUserModification;
	private static User mockUserDeletion;
	private static List<User> dummyDb;

	private static UserRole role;

	//ObjectMapper om = new ObjectMapper()
	//		.registerModule()
	//		.configure();;

	@Autowired
	private static UserController userController;

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private static UserServiceImpl service;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		role = new UserRole();
		role.setId(0);
		role.setName("Shopper");
		role.setDescription("Shop til ya drop");

		service.getUserRoleService().add(role);

		mockUser1 = new User();
		mockUser2 = new User();
		
		mockUserCreation = new User();
		
		UUID tempId = UUID.fromString("5cab4b69-f9ba-4d41-8dc3-5ed24da7027a");
		mockUser1.setId(tempId);
		mockUser1.setEmail("test@gmail.com");
		mockUser1.setFirstName("Levi");
		mockUser1.setLastName("Choi");
		mockUser1.setPassword("Badpassword");
		mockUser1.setUserRole(role);

		tempId = UUID.fromString("22c2db34-749c-45f0-a066-f3bd03bad995");
		mockUser2.setId(tempId);
		mockUser2.setEmail("test@leafbound.com");
		mockUser2.setFirstName("Adam");
		mockUser2.setLastName("Lucas");
		mockUser2.setPassword("goodP@ssword");
		mockUser2.setUserRole(role);


		mockUserModification = mockUser1;
		mockUserModification.setPassword("Passwo");
		mockUserModification.setEmail("email@mail.com");
		
		mockUserDeletion = new User();

		tempId = UUID.fromString("1fd27ac1-439a-4c78-bc1c-f0cb70f66624");
		mockUser2.setId(tempId);
		mockUser2.setEmail("delete@leafbound.com");
		mockUser2.setFirstName("delete");
		mockUser2.setLastName("delete");
		mockUser2.setPassword("deleteP@ssword");
		mockUser2.setUserRole(role);

		dummyDb = new ArrayList<>();
		dummyDb.add(mockUser1);
		dummyDb.add(mockUser2);
	}

	/*
	 * Sanity Check - if this fails, application context is not loaded and all other
	 * tests should fail
	 */
	@Test
	@Order(1)
	@DisplayName("1. AppContext Sanity Test")
	public void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
		assertThat(service).isNotNull();
		assertThat(service.getUserRoleService()).isNotNull();
		
	}

	@Test
	@Order(2)
	@DisplayName("2. Create User - Happy Path Scenerio Test")
	public void testCreateUser() throws Exception {
		// id number of this creation should be 3
		mockUserCreation.setId(3);
		// tell Mockito the behavior that I want this method to act like in the mock environment
		when(service.createUser(mockUserCreation)).thenReturn(true);
		
		//act
		RequestBuilder request = MockMvcRequestBuilders.post("/api/user/register")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockUserCreation))
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockmvc.perform(request).andReturn();
		//assert
		assertThat(result.getResponse().getContentAsString()).isEqualTo(om.writeValueAsString(ClientMessageUtil.CREATION_SUCCESSFUL));
	}

	@Test
	@Order(3)
	@DisplayName("3. Get User by ID - Happy Path Scenerio Test")
	
	public void testGetById() throws Exception {
		when(service.getUserById(1)).thenReturn(mockUser1);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/user?id=1");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(mockUser1), result.getResponse().getContentAsString());
	}

	@Test
	@Order(4)
	@DisplayName("4. Get All Users - Happy Path Scenerio Test")
	
	public void testGetAll() throws Exception {
		when(service.getAllUsers()).thenReturn(dummyDb);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/user/users");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(dummyDb), result.getResponse().getContentAsString());
	}

	@Test
	@Order(5)
	@DisplayName("5. Update an Existing User - Happy Path Scenerio Test")
	
	// @Disabled("Disabled until CreateUserTest is up!")
	public void testUpdateUser() throws Exception {
		when(service.updateUser(mockUserModification)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.put("/api/user/update")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockUserModification))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.UPDATE_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}

	@Test
	@Order(6)
	@DisplayName("6. Delete User - Happy Path Scenerio Test")
	
	public void testDeleteUser() throws Exception {
		when(service.deleteUser(mockUserDeletion)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/user/delete")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockUserDeletion))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.DELETION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
}