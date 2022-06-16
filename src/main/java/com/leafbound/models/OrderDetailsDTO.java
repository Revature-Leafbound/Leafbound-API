package com.leafbound.models;

import java.util.UUID;

public class OrderDetailsDTO {
    private int id;
    private UUID orderId;
    private int productId;
    private int quantity;

    /**
     * 
     */
    public OrderDetailsDTO() {
    }

    /**
     * @param orderId
     * @param productId
     * @param quantity
     */
    public OrderDetailsDTO(UUID orderId, int productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * @param id
     * @param orderId
     * @param productId
     * @param quantity
     */
    public OrderDetailsDTO(int id, UUID orderId, int productId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the orderId
     */
    public UUID getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "OrderDetailsDTO [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", quantity="
                + quantity + "]";
    }

}
