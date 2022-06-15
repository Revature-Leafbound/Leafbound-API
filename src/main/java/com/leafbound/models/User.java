package com.leafbound.models;

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
@Table(name = "users")
@Data
@ApiModel(value = "User", description = "This model serves as a model for the User entity")
public class User {
	@Id
	@GeneratedValue(generator = "uuid4")
	@GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;

	// This is our string values for the generated users table

	@Column(name = "first_name")
	@ApiModelProperty(name = "first name", notes = "A string value that serves as the first name for the user", required = true, value = "first name")
	private String firstName;

	@Column(name = "last_name")
	@ApiModelProperty(name = "last name", notes = "A string value that serves as the last name for the user", required = true, value = "last name")
	private String lastName;

	@Column(name = "password")
	@ApiModelProperty(name = "password", notes = "A string value that serves as the password for the user", required = true, value = "password")
	private String password;

	@Column(name = "email", unique = true, nullable = false)
	@ApiModelProperty(name = "first name", notes = "A string value that serves as the email for the user", required = true, value = "email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	@ApiModelProperty(name = "role id", notes = "An integer value that serves as the role id for the user", required = true, value = "role id")
	private int roleId;
}
