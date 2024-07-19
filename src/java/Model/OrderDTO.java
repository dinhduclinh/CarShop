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
public class OrderDTO {
    private int id;
    private int userId;
    private Date createDate;
    private Date updateDate;
    private String orderStatus;
    private boolean status;

    // Getters and setters

    public OrderDTO() {
    }

    public OrderDTO(int id, int userId, Date createDate, Date updateDate, String orderStatus, boolean status) {
        this.id = id;
        this.userId = userId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.orderStatus = orderStatus;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "id=" + id + ", user_id=" + userId + ", createDate=" + createDate + ", updateDate=" + updateDate + ", orderStatus=" + orderStatus + ", status=" + status + '}';
    }

    
    
}

