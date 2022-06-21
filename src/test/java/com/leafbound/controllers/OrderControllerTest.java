package com.leafbound.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.leafbound.models.Order;
import com.leafbound.models.User;
import com.leafbound.services.OrderService;
import com.leafbound.util.ClientMessageUtil;


@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderControllerTest {
	
	private static Order mockO1;
	private static Order mockO2;

	private static UUID uuid1, uuid2, uuid3, uuid4;
	private static User mockUser1, mockUser2, mockUser3, mockUser4;
	private static Order mockOCre;
	private static Order mockOMod;
	private static Order mockODel;
	private static List<Order> dummyDb;

	ObjectMapper om = new ObjectMapper()
					.registerModule(new JavaTimeModule())
					.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	
	@Autowired
	OrderController orderController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OrderService oserv;
	


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass() :: buidling test object...");
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
		
		String s7 = "3f14d0ab-9605-4a62-a9e4-5ed26688389b";
		String s8 = s7.replace("-", "");
		UUID Uuid4 = new UUID(
		        new BigInteger(s8.substring(0, 16), 16).longValue(),
		        new BigInteger(s8.substring(16), 16).longValue());
		
		
		uuid1=Uuid1;
		uuid2=Uuid2;
		uuid3=Uuid3;
		uuid4=Uuid4;
		
		
		
		mockO1 = new Order(uuid1, mockUser1, LocalDate.of(2021, 4, 16));
		mockO2 = new Order(uuid2, mockUser2, LocalDate.of(2021, 6, 14));
		
		mockOCre = new Order(mockUser3, LocalDate.of(2022, 4, 11));
		mockOMod = mockOCre;
		mockOMod.setId(uuid3);
		mockOMod.setUser(mockUser3);
		mockOMod.setOrderDate(LocalDate.of(2022, 4, 11));
		
		mockODel = new Order(uuid4, mockUser4, LocalDate.of(2020, 2, 2));
		
		dummyDb = new ArrayList<Order>();
		dummyDb.add(mockO1);
		dummyDb.add(mockO2);
	}
	
	
	@Test
	@DisplayName("1. AppContext Sanity Test")
	public void contextLoads() throws Exception {
		assertThat(orderController).isNotNull();
	}
	
	@Test
	@DisplayName("2. Get Order by ID")
	public void GetById_Success() throws Exception {
		when(oserv.getOrderById("0f14d0ab-9605-4a62-a9e4-5ed26688389b")).thenReturn(mockO1);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/order/0f14d0ab-9605-4a62-a9e4-5ed26688389b");
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(mockO1), result.getResponse().getContentAsString());
	}
	
	@Test
	@DisplayName("3. Get Order by ID - Fail")
	public void GetById_Fail() throws Exception {
		when(oserv.getOrderById("1f14d0ab-9605-4a62-a9e4-5ed26688389b")).thenReturn(mockO1);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/order/1f14d0ab-9605-4a62-a9e4-5ed26688389b");
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(mockO1), result.getResponse().getContentAsString());
	}
	
	@Test
	@DisplayName("4. Get Order by Date")
	public void GetByOrderDate_Success() throws Exception {
		when(oserv.getOrderByDate(LocalDate.of(2021, 6, 14))).thenReturn(mockO2);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/order/date/2021-06-14");
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(mockO2), result.getResponse().getContentAsString());
	}
	
	@Test
	@DisplayName("5. Get Order by Date - Fail")
	public void getByOrderDate_Fail() throws Exception {
		when(oserv.getOrderByDate(LocalDate.of(2021, 3, 14))).thenReturn(mockO2);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/order/date/2021-03-14");
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(mockO2), result.getResponse().getContentAsString());
	}
	
	@Test
	@DisplayName("6. Get All Order by Date")
	public void getAllOrders() throws Exception {
		when(oserv.getAllOrders()).thenReturn(dummyDb);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/orderall");
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(dummyDb), result.getResponse().getContentAsString());
	}
	
	@Test
	@DisplayName("7. Update Order")
	public void updateOrder_Success() throws Exception {
		when(oserv.updateOrder(mockOMod)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.put("/api/vl/orderupdate")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockOMod))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.UPDATE_SUCCESSFUL),
				result.getResponse().getContentAsString());
	} 
	
	@Test
	@DisplayName("8. Update Order - Faile")
	public void updateOrder_Fail() throws Exception {
		when(oserv.updateOrder(mockOMod)).thenReturn(false);
		RequestBuilder request = MockMvcRequestBuilders.put("/api/vl/orderupdate")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockOMod))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.UPDATE_FAILED),
				result.getResponse().getContentAsString());
	} 
	
	@Test
	@DisplayName("9. Delete Order")
	public void DeleteOrder_Success() throws Exception {
		when(oserv.deleteOrder(mockODel)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/vl/orderdelete")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockODel))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.DELETION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
	
	@Test
	@DisplayName("10. Delete Order - Fail")
	public void DeleteOrder_Fail() throws Exception {
		when(oserv.deleteOrder(mockODel)).thenReturn(false);
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/vl/orderdelete")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockODel))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.DELETION_FAILED),
				result.getResponse().getContentAsString());
	}
}
