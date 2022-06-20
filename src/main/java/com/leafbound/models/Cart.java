package com.leafbound.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "carts")
@Data
@ApiModel(value = "Carts", description = "This model serves as the basic model for all carts entity API operations.")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "An integer value that serves as the unique identifier for any carts entity.", required = true)
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id", unique = true, nullable = false)
    @ApiModelProperty(name = "carts_product_id", value = "An integer value that serves as the product id for the product.", required = true)
    private Product product;

    @OneToOne
    @JoinColumn(name = "customer_id", unique = true, nullable = false)
    @ApiModelProperty(name = "carts_users_id", value = "An integer value that serves as the customer id for the user.", required = true)
    private User user;

    @Column(name = "quantity", nullable = false)
    @ApiModelProperty(name = "quantity", value = "An integer value describing the quantity of a product.", required = true)
    private int quantity;

    public Cart() {
        super();
    }

    public Cart(int id, Product product, int quantity, User user) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.user = user;
    }

    public Cart(Product product, int quantity, User user) {
        this.product = product;
        this.quantity = quantity;
        this.user = user;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    /**
     * @param id
     * @param product
     * @param quantity
     * @param user
     */
    
    

}
