/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author DLCT
 */
public class ProductDTO {
    private int id;
    private String name;
    private double price;
    private String title;
    private String color;
    private String image;
    private int stock;
    private String description;
        private Date createDate;
    private Date updateDate;
    private int categoryId;
    private boolean status;

    // Getters and setters

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, double price, String title, String color, String image, int stock, String description, Date createDate, Date updateDate, int categoryId, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.title = title;
        this.color = color;
        this.image = image;
        this.stock = stock;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.categoryId = categoryId;
        this.status = status;
    }
    

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "id=" + id + ", name=" + name + ", price=" + price + ", title=" + title + ", color=" + color + ", image=" + image + ", stock=" + stock + ", description=" + description + ", createDate=" + createDate + ", updateDate=" + updateDate + ", categoryId=" + categoryId + ", status=" + status + '}';
    }

    
    
}

