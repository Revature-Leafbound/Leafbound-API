package com.leafbound.models;

import java.util.Date;
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
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
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

	@Column(name = "oder_date")
	@ApiModelProperty(name = "order date", notes = "A date value that serves as a date for orders", required = true, value = "order date")
	private Date orderDate;

}
