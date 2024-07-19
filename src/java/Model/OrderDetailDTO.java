/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DLCT
 */
public class OrderDetailDTO {
    private int id;
    private int quantity;
    private double price;
    private int orderId;
    private int productId;
    private boolean status;

    // Getters and setters

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int id, int quantity, double price, int orderId, int productId, boolean status) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
        this.productId = productId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" + "id=" + id + ", quantity=" + quantity + ", price=" + price + ", orderId=" + orderId + ", productId=" + productId + ", status=" + status + '}';
    }

    
}

