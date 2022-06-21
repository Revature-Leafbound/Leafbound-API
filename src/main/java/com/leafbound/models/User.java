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

@Entity
@Table(name = "users")
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
	private UserRole userRole;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	
}
