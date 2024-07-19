/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DbContext.DBUtils;
import Model.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DLCT
 */
public class ProductDAO {

    public List<ProductDTO> getProducts(int index, String color, String categoryId, String sortField, boolean isAscending, String status, String search) {
        List<ProductDTO> productList = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                // Tạo câu lệnh SQL cơ bản
                String sql = "SELECT [id], [name], [price], [title], [color], [image], [stock], [description], [create_date], [update_date], [category_id], [status] "
                        + "FROM [Car].[dbo].[Product] "
                        + "WHERE [status] = ?";  // Include status condition

                // Nếu có điều kiện màu sắc, thêm vào câu lệnh SQL
                if (color != null && !color.isEmpty()) {
                    sql += " AND [color] = ?";
                }

                // Nếu có điều kiện danh mục, thêm vào câu lệnh SQL
                if (categoryId != null && !categoryId.isEmpty()) {
                    sql += " AND [category_id] = ?";
                }

                // Thêm điều kiện tìm kiếm sử dụng LIKE vào câu lệnh SQL
                if (search != null && !search.isEmpty()) {
                    sql += " AND [name] LIKE ? ";
                }

                // Thêm phần sắp xếp vào câu lệnh SQL nếu có
                if (sortField != null && !sortField.isEmpty()) {
                    switch (sortField.toLowerCase()) {
                        case "price":
                            sql += " ORDER BY [price]";
                            break;
                        // Thêm các trường sắp xếp khác nếu cần
                        default:
                            // Mặc định sắp xếp theo create_date nếu không khớp trường nào
                            sql += " ORDER BY [id]";
                            break;
                    }
                    sql += isAscending ? " ASC" : " DESC";
                } else {
                    // Nếu không có trường sắp xếp được chỉ định, mặc định sắp xếp theo create_date
                    sql += " ORDER BY [id]";
                    sql += isAscending ? " ASC" : " DESC";
                }

                // Thêm phần phân trang vào câu lệnh SQL
                sql += " OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";

                pst = cn.prepareStatement(sql);

                // Thiết lập các tham số
                int paramIndex = 1;
                pst.setString(paramIndex++, status);  // Set status parameter

                if (color != null && !color.isEmpty()) {
                    pst.setString(paramIndex++, color);
                }

                if (categoryId != null && !categoryId.isEmpty()) {
                    pst.setString(paramIndex++, categoryId);
                }

                // Thiết lập tham số tìm kiếm nếu có
                if (search != null && !search.isEmpty()) {
                    pst.setString(paramIndex++, "%" + search + "%");
                }

                pst.setInt(paramIndex++, (index - 1) * 4);

                // Thực hiện truy vấn
                rs = pst.executeQuery();

                // Xử lý kết quả
                while (rs.next()) {
                    ProductDTO product = new ProductDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("title"),
                            rs.getString("color"),
                            rs.getString("image"),
                            rs.getInt("stock"),
                            rs.getString("description"),
                            rs.getDate("create_date"),
                            rs.getDate("update_date"),
                            rs.getInt("category_id"),
                            rs.getBoolean("status")
                    );

                    productList.add(product);
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    public ProductDTO getProductById(int productId) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT * FROM [Car].[dbo].[Product] WHERE [id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, productId);

                rs = pst.executeQuery();

                if (rs.next()) {
                    ProductDTO product = new ProductDTO();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setTitle(rs.getString("title"));
                    product.setColor(rs.getString("color"));
                    product.setImage(rs.getString("image"));
                    product.setStock(rs.getInt("stock"));
                    product.setDescription(rs.getString("description"));
                    product.setCreateDate(rs.getDate("create_date"));
                    product.setUpdateDate(rs.getDate("update_date"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setStatus(rs.getBoolean("status"));

                    return product;
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalProductCount(String color, String categoryId, String search) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                // Sử dụng PreparedStatement để tránh tình trạng SQL injection
                String sql = "SELECT COUNT(*) AS total FROM [Car].[dbo].[Product] WHERE [status] = 1";

                // Thêm điều kiện cho màu (color)
                if (color != null && !color.isEmpty()) {
                    sql += " AND [color] = ?";
                }

                // Thêm điều kiện cho categoryId
                if (categoryId != null && !categoryId.isEmpty()) {
                    sql += " AND [category_id] = ?";
                }

                // Thêm điều kiện cho tìm kiếm (search)
                if (search != null && !search.isEmpty()) {
                    sql += " AND [name] LIKE ?";
                }

                pst = cn.prepareStatement(sql);

                // Đặt giá trị cho các tham số nếu chúng đã được thêm vào câu truy vấn
                int parameterIndex = 1;
                if (color != null && !color.isEmpty()) {
                    pst.setString(parameterIndex++, color);
                }
                if (categoryId != null && !categoryId.isEmpty()) {
                    pst.setString(parameterIndex++, categoryId);
                }

                // Đặt giá trị cho tham số tìm kiếm (search) nếu có
                if (search != null && !search.isEmpty()) {
                    pst.setString(parameterIndex++, "%" + search + "%");
                }

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

    public List<String> getAllColors() {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<String> colors = new ArrayList<>();

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT DISTINCT [color] FROM [Car].[dbo].[Product]";

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String color = rs.getString("color");
                    colors.add(color);
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colors;
    }

    public void insertProduct(ProductDTO product) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [Car].[dbo].[Product] ([name], [price], [title], [color], [image], [stock], [description], [create_date], [update_date], [category_id]) VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE(), GETDATE(), ?)";
                pst = cn.prepareStatement(sql);

                pst.setString(1, product.getName());
                pst.setDouble(2, product.getPrice());
                pst.setString(3, product.getTitle());
                pst.setString(4, product.getColor());
                pst.setString(5, product.getImage());
                pst.setInt(6, product.getStock());
                pst.setString(7, product.getDescription());
                pst.setInt(8, product.getCategoryId());

                pst.executeUpdate();
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(ProductDTO product) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [Car].[dbo].[Product] SET [name] = ?, [price] = ?, [title] = ?, [color] = ?, [image] = ?, [stock] = ?, [description] = ?, [update_date] = GETDATE(), [category_id] = ? WHERE [id] = ?";
                pst = cn.prepareStatement(sql);

                pst.setString(1, product.getName());
                pst.setDouble(2, product.getPrice());
                pst.setString(3, product.getTitle());
                pst.setString(4, product.getColor());
                pst.setString(5, product.getImage());
                pst.setInt(6, product.getStock());
                pst.setString(7, product.getDescription());
                pst.setInt(8, product.getCategoryId());
                pst.setInt(9, product.getId());  // Assuming there's an ID field in your ProductDTO

                pst.executeUpdate();
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int productId) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [Car].[dbo].[Product] SET [status] = 0 WHERE [id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, productId);

                // Execute the update
                pst.executeUpdate();
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductDTO> getProductsByCategoryId(int categoryId) {
        List<ProductDTO> productList = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [id], [name], [price], [title], [color], [image], [stock], [description], [create_date], [update_date], [category_id], [status] "
                        + "FROM [Car].[dbo].[Product] "
                        + "WHERE [status] = 1 AND [category_id] = ?";  // Include status and categoryId conditions

                pst = cn.prepareStatement(sql);
                pst.setInt(1, categoryId);

                rs = pst.executeQuery();

                while (rs.next()) {
                    ProductDTO product = new ProductDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("title"),
                            rs.getString("color"),
                            rs.getString("image"),
                            rs.getInt("stock"),
                            rs.getString("description"),
                            rs.getDate("create_date"),
                            rs.getDate("update_date"),
                            rs.getInt("category_id"),
                            rs.getBoolean("status")
                    );

                    productList.add(product);
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    public List<ProductDTO> getTopProducts() {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<ProductDTO> topProducts = new ArrayList<>();

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT TOP 4 p.* "
                        + "FROM [Car].[dbo].[OrderDetail] od "
                        + "JOIN [Car].[dbo].[Product] p ON od.[product_id] = p.[id] "
                        + "GROUP BY p.[id], p.[name], p.[price], p.[title], p.[color], p.[image], p.[stock], p.[description], p.[create_date], p.[update_date], p.[category_id], p.[status] "
                        + "ORDER BY SUM(od.[quantity]) DESC";

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    ProductDTO product = new ProductDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("title"),
                            rs.getString("color"),
                            rs.getString("image"),
                            rs.getInt("stock"),
                            rs.getString("description"),
                            rs.getDate("create_date"),
                            rs.getDate("update_date"),
                            rs.getInt("category_id"),
                            rs.getBoolean("status")
                    );

                    topProducts.add(product);
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return topProducts;
    }

    public void updateStockByProductId(int productId, int newStock) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [Car].[dbo].[Product] SET [stock] = ? WHERE [id] = ?";
                pst = cn.prepareStatement(sql);

                pst.setInt(1, newStock);
                pst.setInt(2, productId);

                // Execute the update
                pst.executeUpdate();
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
//        }
       int count = dao.getTotalProductCount("", "", "a");
        System.out.println(count);
    }
}
