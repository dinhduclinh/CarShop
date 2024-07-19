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
import Model.OrderDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public List<OrderDTO> getOrders(int index) {
        List<OrderDTO> orderList = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [id], [user_id], [create_date], [update_date], [order_status], [status] "
                        + "FROM [Car].[dbo].[Order] "
                        + "ORDER BY [create_date] DESC "
                        + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, (index - 1) * 4);

                rs = pst.executeQuery();

                while (rs.next()) {
                    OrderDTO order = new OrderDTO(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getDate("create_date"),
                            rs.getDate("update_date"),
                            rs.getString("order_status"),
                            rs.getBoolean("status")
                    );
                    orderList.add(order);
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }
    
    public int getTotalOrderCount() {
    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT COUNT(*) AS total FROM [Car].[dbo].[Order]";
            pst = cn.prepareStatement(sql);

            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        cn.close();
    } catch (Exception e) {
        e.printStackTrace();
    } 
    return 0;
}



    public List<OrderDTO> getOrdersByUserId(int userId) {
        List<OrderDTO> orderList = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [id], [user_id], [create_date], [update_date], [order_status], [status] "
                        + "FROM [Car].[dbo].[Order] "
                        + "WHERE [user_id] = ? "
                        + "ORDER BY [create_date] DESC";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, userId);

                rs = pst.executeQuery();

                while (rs.next()) {
                    OrderDTO order = new OrderDTO(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getDate("create_date"),
                            rs.getDate("update_date"),
                            rs.getString("order_status"),
                            rs.getBoolean("status")
                    );
                    orderList.add(order);
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public OrderDTO insertOrder(OrderDTO order) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String insertOrderSql = "INSERT INTO [Car].[dbo].[Order] ([user_id], [create_date], [update_date], [status]) "
                        + "VALUES (?, GETDATE(), GETDATE(), ?)";
                pst = cn.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);
                pst.setInt(1, order.getUserId());
                pst.setBoolean(2, order.isStatus());

                // Thực hiện chèn
                int affectedRows = pst.executeUpdate();

                // Lấy các khóa được tạo ra (order ID)
                rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    int orderId = rs.getInt(1);
                    order.setId(orderId);
                }

                // Nếu có bản ghi được chèn thành công, trả về đối tượng OrderDTO, ngược lại trả về null
                return (affectedRows > 0) ? order : null;
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateOrderStatus(int orderId, boolean newStatus) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String updateOrderStatusSql = "UPDATE [Car].[dbo].[Order] SET [order_status] = ? WHERE [id] = ?";
                pst = cn.prepareStatement(updateOrderStatusSql);
                pst.setBoolean(1, newStatus);
                pst.setInt(2, orderId);

                // Execute the update
                pst.executeUpdate();
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateStatus(int orderId, boolean status) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String updateOrderStatusSql = "UPDATE [Car].[dbo].[Order] SET [status] = ? WHERE [id] = ?";
                pst = cn.prepareStatement(updateOrderStatusSql);
                pst.setBoolean(1, status);
                pst.setInt(2, orderId);

                // Execute the update
                pst.executeUpdate();
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkExistingOrder(int userId) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String checkExistingOrderSql = "SELECT TOP 1 * FROM [Car].[dbo].[Order] WHERE [user_id] = ? AND [status] = 0 ORDER BY [create_date] DESC";
                pst = cn.prepareStatement(checkExistingOrderSql);
                pst.setInt(1, userId);

                rs = pst.executeQuery();

                return rs.next();  // Trả về true nếu có đơn hàng hiện tại, ngược lại false
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int getLatestOrderId(int userId) {
    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        cn = DBUtils.makeConnection();
        if (cn != null) {
            String getLatestOrderIdSql = "SELECT TOP 1 [id] FROM [Car].[dbo].[Order] WHERE [user_id] = ? ORDER BY [create_date] DESC";
            pst = cn.prepareStatement(getLatestOrderIdSql);
            pst.setInt(1, userId);

            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        cn.close();
    } catch (Exception e) {
        e.printStackTrace();
    } 

    return 0; // Trả về 0 nếu không có đơn hàng nào
}


    public static void main(String[] args) {
        // Thử nghiệm hàm insertOrder
        OrderDAO dao = new OrderDAO();
//        OrderDTO orderToInsert = new OrderDTO();
//        orderToInsert.setUserId(1);  // Điền userId của người dùng cần thử nghiệm
//        orderToInsert.setStatus(true);  // Điền trạng thái mong muốn
//        dao.insertOrder(orderToInsert);
//        System.out.println("Order inserted successfully with ID: " + orderToInsert.getId());

        // Thử nghiệm hàm checkLatestOrder
        boolean latestOrderStatus = dao.checkExistingOrder(1);  // Điền userId của người dùng cần kiểm tra
      int i = dao.getLatestOrderId(2);
        System.out.println("Latest order : " + i);
    }
    // ... Your other methods ...
}
