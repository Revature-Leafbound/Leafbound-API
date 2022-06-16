package com.leafbound.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import com.leafbound.models.UserRole;
import com.leafbound.repositories.UserRoleRepository;
import com.leafbound.services.UserRoleService;
import com.leafbound.services.UserRoleServiceImpl;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRoleServiceTest {

	 @Mock
	 private static UserRoleRepository userRoleRepo;

	 @InjectMocks
	 private static UserRoleServiceImpl userRoleService;
	 private static UserRole mockUserRole1, mockUserRole2, mockUserRole3;
	 private static List<UserRole> dummyDb;
	    
	 @BeforeAll
	 static void setUpBeforeClass() throws Exception {
		 
		 userRoleRepo = Mockito.mock(UserRoleRepository.class);
	        
		 userRoleService = new UserRoleServiceImpl();
	        
		 mockUserRole1 = new UserRole(1, "Manager","Manager1");
		 mockUserRole2 = new UserRole(2, "Customer","Customer1");
		 
		 dummyDb = new ArrayList<UserRole>();
		 dummyDb.add(mockUserRole1);
		 dummyDb.add(mockUserRole2);
		 
	 }
	 @Test
	 @Order(1)
	 @DisplayName("1. getById() test")
	 public void getById() throws Exception{
		when(userRoleRepo.findById(1).get()).thenReturn(mockUserRole1);
		
		assertEquals(mockUserRole1, userRoleService.getById(1));
		
	 }
	 
	 
}




































