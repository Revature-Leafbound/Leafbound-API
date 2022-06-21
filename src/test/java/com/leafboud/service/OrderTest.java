package com.leafboud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.leafbound.models.Order;
import com.leafbound.repositories.OrderRepository;
import com.leafbound.services.OrderService;
import com.leafbound.services.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderTest {
	@Mock
	private static OrderRepository orepo;
	
	@MockBean
	private static OrderService oserv;
	
	@InjectMocks
	private static OrderServiceImpl oservimpl;
	
	private static Order o1, o2, o3;
	static List<Order> dummyDb;
	private static UUID uuid1;
	private static UUID uuid2;
	private static UUID uuid3;
	
	
	@BeforeAll
	static void setUpBeforeClass() {
		
		uuid1 = UUID.randomUUID();
    uuid2 = UUID.randomUUID();
    uuid3 = UUID.randomUUID();
		
		orepo = Mockito.mock(OrderRepository.class);
		
		o1 = new Order(uuid1, 20, LocalDate.now());
		o2 = new Order(uuid2, 50, LocalDate.now());
		o3 = new Order(uuid3, 24, LocalDate.now());
		
		dummyDb = new ArrayList<Order>();
		dummyDb.add(o1);
		dummyDb.add(o2);
		dummyDb.add(o3);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Find order by UUID")
	void testFindOrderByUUID() {
		
		when(orepo.getById(uuid1)).thenReturn(o1);
		
		assertEquals(o1, oserv.getOrderById(uuid1));
		
	}
}