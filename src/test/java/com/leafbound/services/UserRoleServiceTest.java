package com.leafbound.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import com.leafbound.models.UserRole;
import com.leafbound.repositories.UserRoleRepository;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRoleServiceTest {

	@Mock
	private UserRoleRepository userRoleRepo;

	@InjectMocks
	private UserRoleServiceImpl userRoleService;
	
	
	
	private static UserRole mockUserRole1, mockUserRole2, mockUserRole3;
	private List<UserRole> dummyDb;

	@BeforeAll
	public void setUpBeforeClass() throws Exception {

		userRoleRepo = Mockito.mock(UserRoleRepository.class);
		
		userRoleService = new UserRoleServiceImpl(userRoleRepo);
		
		

	}
	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
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
	@DisplayName("1. Mock Validation Test")
	public void checkMockInjection() {
		
		assertThat(userRoleRepo).isNotNull();
		assertThat(userRoleService).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. getById() test")
	public void getById() throws Exception {
		
		when(userRoleRepo.findById(1)).thenReturn(Optional.of(mockUserRole1));
		assertEquals(userRoleService.getById(1), mockUserRole1);

	}
	@Test
	@Order(3)
	@DisplayName("3. getById() failure test")
	public void getByIdFail() throws Exception {
	
		when(userRoleRepo.findById(1)).thenReturn(Optional.of(mockUserRole1));
		assertNotEquals(mockUserRole2, userRoleRepo.findById(1));

	}

	@Test
	@Order(4)
	@DisplayName("4. add() test")
	public void add() throws Exception {
		mockUserRole3 = mockUserRole2;
		mockUserRole3.setId(3);

		when(userRoleRepo.save(mockUserRole3)).thenReturn(mockUserRole3);
		assertEquals(true, userRoleService.add(mockUserRole3));

	}

	@Test
	@Order(5)
	@DisplayName("5. remove() test")
	public void remove() throws Exception {
		userRoleRepo.deleteById(mockUserRole2.getId());
	    assertEquals(true, userRoleService.remove(mockUserRole2.getId()));
	}

}
