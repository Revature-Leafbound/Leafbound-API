package com.leafbound.models;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Orders", description = "This model serves as model for Order entyty")
public class Order {

	@Id
	@GeneratedValue(generator = "uuid4")
	@GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@ApiModelProperty(name = "user_id", notes = "An UUID value that serves as the user id for orders", required = true, value = "user id")
	private User user;

	@Column(name = "order_date")
	@ApiModelProperty(name = "order date", notes = "A date value that serves as a date for orders", required = true, value = "order date")
	private LocalDate orderDate;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	

}
