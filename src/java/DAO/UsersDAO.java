/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DbContext.DBUtils;
import Model.UserDTO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DLCT
 */
public class UsersDAO {

    public UserDTO checkLogin(String userName, String password) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT * FROM [Car].[dbo].[User] WHERE [username] = ? AND [password] = ? AND [status] = 1";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userName);
                pst.setString(2, password);

                rs = pst.executeQuery();

                if (rs.next()) {
                    UserDTO user = new UserDTO();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFullname(rs.getString("fullname"));
                    user.setAddress(rs.getString("address"));
                    user.setPhone(rs.getString("phone"));
                    user.setRoleId(rs.getInt("role_id"));
                    user.setCreateDate(rs.getDate("create_date"));
                    user.setUpdateDate(rs.getDate("update_date"));
                    user.setStatus(rs.getBoolean("status"));

                    return user;
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // If login fails or any exception occurs
    }
    
    public UserDTO getUserById(int userId) {
    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT * FROM [dbo].[User] WHERE [id] = ?";
            pst = cn.prepareStatement(sql);
            pst.setInt(1, userId);

            rs = pst.executeQuery();

            if (rs.next()) {
                UserDTO user = new UserDTO();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));

                return user;
            }
        }
        cn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null; // Trả về null nếu có lỗi xảy ra hoặc không tìm thấy người dùng
}


    public boolean insertUser(UserDTO user) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[User] ([username], [fullname], [password], [address], [phone], [role_id], [create_date], [update_date], [status]) "
                        + "VALUES (?, ?, ?, ?, ?, ?, GETDATE(), GETDATE(), 1)";

                pst = cn.prepareStatement(sql);

                pst.setString(1, user.getUsername());
                pst.setString(2, user.getFullname());
                pst.setString(3, user.getPassword());
                pst.setString(4, user.getAddress());
                pst.setString(5, user.getPhone());
                pst.setInt(6, user.getRoleId());

                int rowsAffected = pst.executeUpdate();

                // Trả về true nếu có ít nhất một dòng được thêm vào
                return rowsAffected > 0;
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi xảy ra
    }

    public boolean checkByUsername(String username) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT COUNT(*) AS count FROM [dbo].[User] WHERE [username] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, username);

                rs = pst.executeQuery();

                if (rs.next()) {
                    int count = rs.getInt("count");
                    // Nếu count > 0, tức là tên người dùng đã tồn tại
                    return count > 0;
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false; // Trả về false nếu có lỗi xảy ra
    }

    public UserDTO getByUsername(String username) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT * FROM [dbo].[User] WHERE [username] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, username);

                rs = pst.executeQuery();

                if (rs.next()) {
                    UserDTO user = new UserDTO();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    // Gán các giá trị khác từ ResultSet cho đối tượng UserDTO

                    return user;
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu có lỗi xảy ra hoặc không tìm thấy người dùng
    }

    public boolean updatePasswordById(String userId, String newPassword) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[User] SET [password] = ? WHERE [id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, newPassword);
                pst.setString(2, userId);

                int rowsAffected = pst.executeUpdate();
                // Nếu có bản ghi nào được cập nhật, trả về true
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi xảy ra
    }

    public boolean DeleteUserSetFalse(String Id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtils.makeConnection();
            if (connection != null) {
                String sql = "UPDATE [dbo].[User] SET  [Status] = 0 WHERE [Id] = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, Id);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        UsersDAO dao = new UsersDAO();
        UserDTO check = dao.getByUsername("user");
        boolean update = dao.updatePasswordById(String.valueOf(check.getId()) , "1234567");
        UserDTO check2 = dao.getByUsername("user");
        System.out.println(check);
        System.out.println(update);
        System.out.println(check2);
    }
}
