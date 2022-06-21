package com.leafbound.models;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class OrderDTO {

	private UUID id;
	private User user;
	private LocalDate orderDate;

	
	public OrderDTO(User user, LocalDate orderDate) {
		super();
		this.user = user;
		this.orderDate = orderDate;
	}

	public OrderDTO(UUID id, User user, LocalDate orderDate) {
		super();
		this.id = id;
		this.user = user;
		this.orderDate = orderDate;
	}

	public OrderDTO() {
		super();
	}

}
