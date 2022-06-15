package com.leafbound.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "carts")
@Data
@ApiModel(value = "carts", description = "This model serves as the basic model for all carts entity API operations.")
public class Carts {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "An integer value that serves as the unique identifier for any carts entity.", required = true)
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id", unique = true, nullable = false)
    @ApiModelProperty(name = "carts_product_id", value = "An integer value that serves as the product id for the product.", required = true)
    private Products product;

    @OneToOne
    @JoinColumn(name = "customer_id", unique = true, nullable = false)
    @ApiModelProperty(name = "carts_users_id", value = "An integer value that serves as the customer id for the user.", required = true)
    private User user;    

    @Column(name = "quantity", nullable = false)
    @ApiModelProperty(name = "quantity", value = "An integer value describing the quantity of a product.", required = true)
    private int quantity;


    public Carts() {
        super();
    }



    public Carts(int id, Products product, int quantity, User user) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.user = user;
    }



    public Carts(Products product, int quantity, User user) {
        this.product = product;
        this.quantity = quantity;
        this.user = user;
    }

    /**
     * @param id
     * @param product
     * @param quantity
     * @param user
     */
    



}
