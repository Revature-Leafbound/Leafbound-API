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

@Entity
@Table(name = "order_details")
@ApiModel(value = "OrderDetails", description = "This model serves as the basic model for all OrderDetails entity API operations.")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "An integer value that serves as the unique identifier for any order details entity.", required = true)
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id", unique = true, nullable = false)
    @ApiModelProperty(name = "order_details_product_id", value = "An integer value that serves as the product id for the product.", required = true)
    private Product product;

    @Column(name = "quantity", nullable = false)
    @ApiModelProperty(name = "quantity", value = "An integer value describing the quantity of a product.", required = true)
    private int quantity;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true, nullable = false)
    @ApiModelProperty(name = "order_details_order_id", value = "An integer value that serves as the order id for the order.", required = true)
    private Order order;

    public OrderDetails() {
        super();
    }

    public OrderDetails(int id, Product product, int quantity, Order order) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.order = order;
    }

    public OrderDetails(Product product, int quantity, Order order) {
        this.product = product;
        this.quantity = quantity;
        this.order = order;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

    /**
     * @param id
     * @param product
     * @param quantity
     * @param order
     */
    
    

}
