package com.leafbound.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "roles")
@ApiModel(value = "User_Roles", description = "This class serves as a model for the User Role entity")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "An integer value serving as the unique identifier for any user role entity.", required = true)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    @ApiModelProperty(name = "name", value = "A String value representing the role of the user.", required = true)
    private String name;

    @Column(name = "description", unique = true, nullable = false)
    @ApiModelProperty(name = "description", value = "A String value describing a specific user role.", required = true)
    private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    

}
