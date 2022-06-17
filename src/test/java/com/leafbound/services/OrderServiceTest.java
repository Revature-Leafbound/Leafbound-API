package com.leafbound.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.leafbound.models.Order;
import com.leafbound.models.User;
import com.leafbound.repositories.OrderRepository;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderServiceTest {
	
	@Mock
	private static OrderRepository orepo;
	
	@MockBean
	private static OrderService oserv;
	
	@InjectMocks
	private static OrderServiceImpl oservimpl;
	
	private static Order o1, o2, o3;
	static List<Order> dummyDb;

	private static User user1;
	private static User user2;
	private static User user3;
	
	
	@BeforeAll
	static void setUpBeforeClass() {
		
		user1 = new User();

		
		orepo = Mockito.mock(OrderRepository.class);
		
		

	}
	
	
}