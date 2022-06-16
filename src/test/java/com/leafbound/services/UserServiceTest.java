package com.leafbound.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
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
import org.springframework.beans.factory.annotation.Qualifier;

import com.leafbound.models.User;
import com.leafbound.models.UserDTO;
import com.leafbound.models.UserRole;
import com.leafbound.repositories.UserRepository;
import com.leafbound.repositories.UserRoleRepository;
import com.leafbound.services.UserServiceImpl;
import com.leafbound.services.UserRoleServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
	@Mock(name="UserRepo")
	private static UserRepository mockrepo;

	@InjectMocks
	@Qualifier(value = "UserRepo")
	private static UserServiceImpl uServ;
	
	private static User u1, u2;
	private static UserDTO dummyTransfer;
	static List<User> dummyDB;
	
	@Mock(name="RoleRepo")
	private static UserRoleRepository mockrole;
	
	@InjectMocks
	@Qualifier(value="RoleRepo")
	private static UserRoleServiceImpl rServ;
	
	private static UserRole role;
	static List<UserRole> dummyRoleDB;

	Logger log = Logger.getLogger(UserServiceTest.class);
	
	@BeforeAll
	static void setupBefore() throws Exception {
		mockrepo = Mockito.mock(UserRepository.class);
		uServ = new UserServiceImpl();
		
		mockrole = Mockito.mock(UserRoleRepository.class);
		rServ = new UserRoleServiceImpl();

		role = new UserRole();
		role.setId(0);
		role.setName("Shopper");
		role.setDescription("Shop til ya drop");
	
		dummyRoleDB = new ArrayList<UserRole>();
		dummyRoleDB.add(role);
		
		u1 = new User();
		UUID tempId = UUID.fromString("5cab4b69-f9ba-4d41-8dc3-5ed24da7027a");
		u1.setId(tempId);
		u1.setEmail("test@gmail.com");
		u1.setFirstName("Levi");
		u1.setLastName("Choi");
		u1.setPassword("Badpassword");
		
		
		//sanity test
		mockrole.findById(0);
		rServ.add(role);
		u1.setUserRole(rServ.getById(0));
		tempId = UUID.fromString("22c2db34-749c-45f0-a066-f3bd03bad995");
		u2 = new User();
		u2.setId(tempId);
		u2.setEmail("test@leafbound.com");
		u2.setFirstName("Adam");
		u2.setLastName("Lucas");
		u2.setPassword("goodP@ssword");
		u1.setUserRole(rServ.getById(0));
		
		dummyTransfer = new UserDTO();
		
		dummyDB = new ArrayList<User>();
		dummyDB.add(u1);
		dummyDB.add(u2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. Mock Validation")
	void checkMockInjection() {
		assertThat(mockrepo).isNotNull();
		assertThat(uServ).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Create User")
	void testCreateUser() {
		User u3 = new User();
		
		UUID tempId = UUID.fromString("1fd27ac1-439a-4c78-bc1c-f0cb70f66624");
		u3.setId(tempId);
		u3.setEmail("test@yahoo.com");
		u3.setFirstName("Yugal");
		u3.setLastName("Subedi");
		u3.setPassword("N3Wpassword");
		u3.setUserRole(rServ.getById(0));
		
		when(mockrepo.save(u3)).thenReturn(u3);
		
		dummyTransfer.setId(u3.getId());
		dummyTransfer.setEmail(u3.getEmail());
		dummyTransfer.setFirstName(u3.getFirstName());
		dummyTransfer.setLastName(u3.getLastName());
		dummyTransfer.setPassword(u3.getPassword());
		dummyTransfer.setRoleId(u3.getUserRole().getId());
		
		assertEquals(dummyTransfer, uServ.createUser(dummyTransfer));
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Get User By Id")
	void testGetUserById() {
		when(uServ.getUserById(u1.getId().toString())).thenReturn(u1);
		log.info("user id: " + u1.getId());
		
		assertEquals(u1, uServ.getUserById(u1.getId().toString()));
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
