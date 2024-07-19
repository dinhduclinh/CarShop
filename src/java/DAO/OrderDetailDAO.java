/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author DLCT
 */
import DbContext.DBUtils;
import Model.OrderDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {

    // ... Your other methods ...

    public List<OrderDetailDTO> getOrderDetails(int index) {
        List<OrderDetailDTO> orderDetailList = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [id],[quantity],[price],[order_id], [product_id], [status] "
                        + "FROM [Car].[dbo].[OrderDetail] "
                        + "ORDER BY [id] OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, (index - 1) * 4);

                rs = pst.executeQuery();

                while (rs.next()) {
                    OrderDetailDTO orderDetail = new OrderDetailDTO(
                            rs.getInt("id"),
                            rs.getInt("quantity"),
                            rs.getDouble("price"),
                            rs.getInt("order_id"),
                            rs.getInt("product_id"),
                            rs.getBoolean("status")
                    );
                    orderDetailList.add(orderDetail);
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return orderDetailList;
    }

    public List<OrderDetailDTO> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetailDTO> orderDetailList = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [id],[quantity],[price], [order_id], [product_id], [status] "
                        + "FROM [Car].[dbo].[OrderDetail] "
                        + "WHERE [order_id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, orderId);

                rs = pst.executeQuery();

                while (rs.next()) {
                    OrderDetailDTO orderDetail = new OrderDetailDTO(
                            rs.getInt("id"),
                            rs.getInt("quantity"),
                            rs.getDouble("price"),
                            rs.getInt("order_id"),
                            rs.getInt("product_id"),
                            rs.getBoolean("status")
                    );
                    orderDetailList.add(orderDetail);
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderDetailList;
    }

    public void insertOrderDetail(OrderDetailDTO orderDetail) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [Car].[dbo].[OrderDetail] ([quantity],[price], [order_id], [product_id], [status]) "
                        + "VALUES (?, ?, ?, ?, ?)";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, orderDetail.getQuantity());
                pst.setDouble(2, orderDetail.getPrice());
                pst.setInt(3, orderDetail.getOrderId());
                pst.setInt(4, orderDetail.getProductId());
                pst.setBoolean(5, orderDetail.isStatus());

                // Execute the insert
                pst.executeUpdate();
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public double getTotalPriceOrderDetailByOrderId(String orderId) {
    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT SUM([quantity] * [price]) AS total FROM [Car].[dbo].[OrderDetail] WHERE [order_id] = ?";
            pst = cn.prepareStatement(sql);
            
            pst.setString(1, orderId);

            // Execute the query
            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }
        }
        cn.close();
    } catch (Exception e) {
        e.printStackTrace();
    } 

    return 0;
}
    
    public int getAllQuantity() {
    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    int totalQuantity = 0;

    try {
        cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT SUM(quantity) AS totalQuantity FROM [Car].[dbo].[OrderDetail]";
            pst = cn.prepareStatement(sql);

            // Execute the query
            rs = pst.executeQuery();

            if (rs.next()) {
                totalQuantity = rs.getInt("totalQuantity");
            }
        }
        cn.close();
    } catch (Exception e) {
        e.printStackTrace();
    } 
    return totalQuantity;
}


public int getAllQuantityOrderDetail(int orderId) {
    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT SUM([quantity]) AS totalQuantity FROM [Car].[dbo].[OrderDetail] WHERE [order_id] = ?";
            pst = cn.prepareStatement(sql);
            
            pst.setInt(1, orderId);

            // Execute the query
            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("totalQuantity");
            }
        }
        cn.close();
    } catch (Exception e) {
        e.printStackTrace();
    } 

    return 0;
}


    public void updateOrderDetail(int id, int quantity, double price) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [Car].[dbo].[OrderDetail] SET [quantity] WHERE [id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                pst.setInt(2, quantity);

                // Execute the update
                pst.executeUpdate();
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void deleteOrderDetail(int Id) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [Car].[dbo].[OrderDetail] SET [status] = 0 WHERE [id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, Id);

                // Execute the delete (update status to 0)
                pst.executeUpdate();
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    // ... Your other methods ...
    public static void main(String[] args) {
        OrderDetailDAO dao = new OrderDetailDAO();
        double t =  dao.getTotalPriceOrderDetailByOrderId("1");
        System.out.println(t);
    }
}

