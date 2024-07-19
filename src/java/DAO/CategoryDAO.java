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
import java.sql.Connection;
import java.sql.PreparedStatement;
import DbContext.DBUtils;
import Model.CategoryDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public List<CategoryDTO> getAllCategories() {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<CategoryDTO> categories = new ArrayList<>();

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [id], [name], [status] FROM [Car].[dbo].[Category]WHERE [status] = 1";

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    boolean status = rs.getBoolean("status");

                    CategoryDTO category = new CategoryDTO(id, name, status);
                    categories.add(category);
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }
    public CategoryDTO getCategoryById(int categoryId) {
    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    CategoryDTO category = null;

    try {
        cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [name], [status] FROM [Car].[dbo].[Category] WHERE [id] = ?";

            pst = cn.prepareStatement(sql);
            pst.setInt(1, categoryId);

            rs = pst.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean status = rs.getBoolean("status");

                category = new CategoryDTO(id, name, status);
            }
        }
        cn.close();
    } catch (Exception e) {
        e.printStackTrace();
    } 

    return category;
}

    public void insertCategory(CategoryDTO category) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [Car].[dbo].[Category] ([name], [status]) VALUES (?, ?)";
                pst = cn.prepareStatement(sql);

                pst.setString(1, category.getName());
                pst.setBoolean(2, category.isStatus());

                pst.executeUpdate();
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void updateCategory(CategoryDTO category) {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [Car].[dbo].[Category] SET [name] = ?, [update_date] = GETDATE() WHERE [id] = ?";
                pst = cn.prepareStatement(sql);

                pst.setString(1, category.getName());
                pst.setInt(2, category.getId());  // Assuming there's an ID field in your CategoryDTO

                pst.executeUpdate();
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
        List<CategoryDTO> list = dao.getAllCategories();
        for (CategoryDTO categoryDTO : list) {
            System.out.println(categoryDTO);
        }
    }
}

