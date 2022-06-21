package com.leafbound.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
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
import org.springframework.boot.test.mock.mockito.MockBean;

import com.leafbound.models.Order;
import com.leafbound.models.User;
import com.leafbound.repositories.OrderRepository;



@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderServiceTest {
	
	@Mock
	private static OrderRepository orepo;
	
	@MockBean
	private static OrderService oserv;
	
	@InjectMocks
	private static OrderServiceImpl oservimpl;
	
	private static Order o1, o2;
	static List<Order> dummyDb;
	private static User u1, u2, u3;
	private static UUID uuid1, uuid2, uuid3;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		orepo = Mockito.mock(OrderRepository.class);
		oserv = new OrderServiceImpl(orepo);
		
		String s = "0f14d0ab-9605-4a62-a9e4-5ed26688389b";
		String s2 = s.replace("-", "");
		UUID Uuid1 = new UUID(
		        new BigInteger(s2.substring(0, 16), 16).longValue(),
		        new BigInteger(s2.substring(16), 16).longValue());
		
		String s3 = "1f14d0ab-9605-4a62-a9e4-5ed26688389b";
		String s4 = s3.replace("-", "");
		UUID Uuid2 = new UUID(
		        new BigInteger(s4.substring(0, 16), 16).longValue(),
		        new BigInteger(s4.substring(16), 16).longValue());
		
		String s5 = "2f14d0ab-9605-4a62-a9e4-5ed26688389b";
		String s6 = s5.replace("-", "");
		UUID Uuid3 = new UUID(
		        new BigInteger(s6.substring(0, 16), 16).longValue(),
		        new BigInteger(s6.substring(16), 16).longValue());
		
		uuid1=Uuid1;
		uuid2=Uuid2;
		uuid3=Uuid3;
		
		
		o1 = new Order(uuid1, u1, LocalDate.of(2011, 1, 1));
		o2 = new Order(uuid2, u2, LocalDate.of(2022, 2, 2));
		
		dummyDb = new ArrayList<Order>();
		dummyDb.add(o1);
		dummyDb.add(o2);
	}
	
	@Test
	@DisplayName("1. Mock Validation Sanity Test")
	public void checkMockInjection(){
		assertThat(orepo).isNotNull();
		assertThat(oserv).isNotNull();
	}
	
	@Test
	@DisplayName("2. Get Order By Id - Success")
	public void testGetById_Success() throws Exception {
		when(oserv.getOrderById("0f14d0ab-9605-4a62-a9e4-5ed26688389b")).thenReturn(o1);
		assertEquals(o1, oserv.getOrderById("0f14d0ab-9605-4a62-a9e4-5ed26688389b"));
	}
	
	@Test
	@DisplayName("3. Get Order By Id - Failure")
	public void testGetById_Failure() throws Exception {
		when(oserv.getOrderById("1f14d0ab-9605-4a62-a9e4-5ed26688389b")).thenReturn(o1);
		assertNotEquals(o2, oserv.getOrderById("1f14d0ab-9605-4a62-a9e4-5ed26688389b"));
	}
	
	@Test
	@DisplayName("4. Get Order By Date - Success")
	public void testGetByOrderDate_Success() throws Exception {
		when(oserv.getOrderByDate(LocalDate.of(2011, 1, 1))).thenReturn(o1);
		assertEquals(o1, oserv.getOrderByDate(LocalDate.of(2011, 1,1)));
	}
	
	@Test
	@DisplayName("5. Get Order By Date - Failure")
	public void testGetByOrderDate_Failure() throws Exception {
		when(oserv.getOrderByDate(LocalDate.of(2022, 2, 2))).thenReturn(o1);
		assertNotEquals(o2, oserv.getOrderByDate(LocalDate.of(2022, 2,2)));
	}
	
	@Test
	@DisplayName("6. Get All Orders - Success")
	public void testGetAllOrder_Success() throws Exception {
		when(oserv.getAllOrders()).thenReturn(dummyDb);
		assertEquals(dummyDb, oserv.getAllOrders());
	}
	
	@Test
	@DisplayName("7. Get All Orders - Failure")
	public void testGetAllOrder_Failure() throws Exception {
		when(oserv.getAllOrders()).thenReturn(null);
		assertNotEquals(dummyDb, oserv.getAllOrders());
	}
	
	@Test
	@DisplayName("8. Update Order - Success")
	public void testUpdateOrder_Success() throws Exception {
		o2.setId(uuid3);
		o2.setOrderDate(LocalDate.of(2002, 2, 2));
		o2.setUser(u3);
		
		when(oserv.getOrderById("2f14d0ab-9605-4a62-a9e4-5ed26688389b")).thenReturn(o2);
		assertEquals(true, oserv.updateOrder(o2));
	}
	
	@Test
	@DisplayName("9. Update Order - Failure")
	public void testUpdateOrder_Failure() throws Exception {
		o2.setId(uuid3);
		o2.setOrderDate(LocalDate.of(2002, 2, 2));
		o2.setUser(u3);
		
		when(oserv.getOrderById("2f14d0ab-9605-4a62-a9e4-5ed26688389b")).thenReturn(o2);
		assertFalse(oserv.updateOrder(o2));
	}
	
	@Test
	@DisplayName("10. Delete Order - Success")
	public void testDeleteOrder_Success() throws Exception {
		doNothing().when(orepo).delete(o2);
		assertEquals(true, oserv.deleteOrder(o2));
	}
	
	@Test
	@DisplayName("11. Delete Order - Failure")
	public void testDeleteOrder_Failure() throws Exception {
		when(oserv.deleteOrder(o2)).thenReturn(false);
		assertFalse(oserv.deleteOrder(o2));
	}
}
