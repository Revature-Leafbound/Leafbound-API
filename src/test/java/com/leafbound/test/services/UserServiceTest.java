package com.leafbound.test.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

import com.leafbound.models.User;
import com.leafbound.models.UserDTO;
import com.leafbound.repositories.UserRepository;
import com.leafbound.services.UserServiceImpl;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
	@Mock
	private static UserRepository mockdao;

	@InjectMocks
	private static UserServiceImpl uServ;
	
	private static User u1, u2;
	private static UserDTO dummyTransfer;
	static List<User> dummyDB;
	
	@BeforeAll
	static void setupBefore() throws Exception {
		mockdao = Mockito.mock(UserRepository.class);
		
		uServ = new UserServiceImpl();
		
		u1 = new User();
		u1.setEmail("test@gmail.com");
		u1.setFirstName("Levi");
		u1.setLastName("Choi");
		u1.setPassword("Badpassword");
		u1.setRoleId(0);
		
		u2 = new User();
		u2.setEmail("test@leafbound.com");
		u2.setFirstName("Adam");
		u2.setLastName("Lucas");
		u2.setPassword("goodP@ssword");
		u2.setRoleId(0);
		
		dummyTransfer = new UserDTO();
		
		dummyDB = new ArrayList<User>();
		dummyDB.add(u1);
		dummyDB.add(u2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. Mock Validation")
	void checkMockInjection() {
		assertThat(mockdao).isNotNull();
		assertThat(uServ).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Create User")
	void testCreateUser() {
		User u3 = new User();
		
		when(mockdao.save(u3)).thenReturn(u3);
		dummyTransfer.setId(u3.getId());
		dummyTransfer.setEmail(u3.getEmail());
		dummyTransfer.setFirstName(u3.getFirstName());
		dummyTransfer.setLastName(u3.getLastName());
		dummyTransfer.setPassword(u3.getPassword());
		dummyTransfer.setRoleId(u3.getRoleId());
		
		assertEquals(dummyTransfer, uServ.createUser(dummyTransfer));
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Get User By Id")
	void testGetUserById() {
		when(uServ.getUserById(u1.getId())).thenReturn(u1);
		System.out.println(u1.getId());
		
		assertEquals(u1, uServ.getUserById(u1.getId()));
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Get All Users")
	void testGetAllUsers() {
		when(uServ.getAllUsers()).thenReturn(dummyDB);
		
		assertEquals(dummyDB, uServ.getAllUsers());
	}
	
	@Test
	@Order(5)
	@DisplayName("5. Update User")
	void testUpdateUser() {
		u1.setFirstName("TestSuccessful");
		
		boolean result = false;
		
		when(uServ.updateUser(u1)).thenReturn(result);
		
		assertEquals(result, uServ.updateUser(u1));
	}
}
