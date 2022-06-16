package com.leafbound.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Entity
@Table(name="orders")
@Data
@ApiModel(value="Orders", description="This model serves as model for Order entyty")
public class Order {
	
	@Id
	@GeneratedValue(generator = "uuid4")
	@GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id")
	private UUID id;
	
	@OneToMany
	@JoinColumn(name="user_id", nullable=false)
	@ApiModelProperty(name="user_id", notes="An integer value that serves as the user id for orders", required=true, value="user id")
	private int userId;
	
	@Column(name="order_date")
	@ApiModelProperty(name="order date", notes="A date value that serves as a date for orders", required=true, value="order date")
	private LocalDate orderDate;

	public Order() {
		super();
	}

	public Order(int userId, LocalDate orderDate) {
		super();
		this.userId = userId;
		this.orderDate = orderDate;
	}

	public Order(UUID id, int userId, LocalDate orderDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.orderDate = orderDate;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", orderDate=" + orderDate + "]";
	}

	

}
