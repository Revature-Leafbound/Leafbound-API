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
	static List<User> dummyDB;
	
	@BeforeAll
	static void setupBefore() throws Exception {
		mockdao = Mockito.mock(UserRepository.class);
		
		uServ = new UserServiceImpl();
		
		u1 = new User();
		u2 = new User();
		
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
	@DisplayName("2. Create User - Happy Path Test")
	void testCreateUser() {
		User u3 = new User();
		
		when(mockdao.save(u3)).thenReturn(u3);
		
		assertEquals(true, uServ.createUser(u3));
	}
}
