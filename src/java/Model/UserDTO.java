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
public class UserDTO {
    private int id;
    private String username;
    private String fullname;
    private String password;
    private String address;
    private String phone;
    private int roleId;
    private Date createDate;
    private Date updateDate;
    private boolean status;

    public UserDTO() {
    }

    public UserDTO(int id, String username, String fullname, String password, String address, String phone, int roleId, Date createDate, Date updateDate, boolean status) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.roleId = roleId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id=" + id + ", username=" + username + ", fullname=" + fullname + ", password=" + password + ", address=" + address + ", phone=" + phone + ", roleId=" + roleId + ", createDate=" + createDate + ", updateDate=" + updateDate + ", status=" + status + '}';
    }

}


