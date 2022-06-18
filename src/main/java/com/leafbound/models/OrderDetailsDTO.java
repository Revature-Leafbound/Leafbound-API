package com.leafbound.models;

public class OrderDetailsDTO {
    private int id;
    private String orderId;
    private int productId;
    private int quantity;

    public OrderDetailsDTO() {
        super();
    }

    /**
     * @param orderId
     * @param productId
     * @param quantity
     */
    public OrderDetailsDTO(String orderId, int productId, int quantity) {
        super();
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
    public OrderDetailsDTO(int id, String orderId, int productId, int quantity) {
        super();
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
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
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
