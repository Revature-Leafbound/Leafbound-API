package com.leafbound.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringRunner;

import com.leafbound.models.UserRole;
import com.leafbound.repositories.UserRoleRepository;

@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRoleServiceTest {

	@Mock
	@Autowired
	private UserRoleRepository userRoleRepo;

	@Autowired
	private UserRoleService userRoleService;

	private UserRole mockUserRole1, mockUserRole2, mockUserRole3;
	private List<UserRole> dummyDb;

	@BeforeAll
	public void setUpBeforeClass() throws Exception {

		userRoleRepo = Mockito.mock(UserRoleRepository.class);

		userRoleService = new UserRoleServiceImpl();

		mockUserRole1 = new UserRole();

		mockUserRole1.setId(1);
		mockUserRole1.setName("Manager");
		mockUserRole1.setDescription("Manager1");

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
	@DisplayName("1. getById() test")
	public void getById() throws Exception {
		when(userRoleRepo.findById(1).get()).thenReturn(mockUserRole1);

		assertEquals(userRoleService.getById(1), mockUserRole1);

	}

	@Test
	@Order(2)
	@DisplayName("2. add() test")
	public void add() throws Exception {
		mockUserRole3 = mockUserRole2;
		mockUserRole3.setId(3);

		when(userRoleRepo.save(mockUserRole3)).thenReturn(mockUserRole3);

		assertEquals(true, userRoleService.add(mockUserRole3));

	}

	@Test
	@Order(3)
	@DisplayName("3. remove() test")
	public void remove() throws Exception {

	}

}
