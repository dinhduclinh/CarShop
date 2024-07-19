/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.CategoryDTO;
import Model.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author DLCT
 */
public class HomePageController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ProductDAO productDAO = new ProductDAO();
            CategoryDAO cateDAO = new CategoryDAO();
            
            String indexParam = request.getParameter("index");
            int index = (indexParam != null && !indexParam.isEmpty()) ? Integer.parseInt(indexParam) : 1;
            String color = request.getParameter("color");
            
            String categoryId = request.getParameter("categoryId");
            String search = request.getParameter("search");
            String sortField = request.getParameter("sortField");
            String sortDirection = request.getParameter("sortDirection");
            boolean isAscending = (sortDirection != null && sortDirection.equalsIgnoreCase("asc"));

            // Lấy danh sách sản phẩm theo các tham số
            List<ProductDTO> productList = productDAO.getProducts(index, color, categoryId, sortField, isAscending, "1", search);
            List<ProductDTO> topProduct = productDAO.getTopProducts();
            // Lấy tổng số lượng sản phẩm (để phục vụ việc phân trang)
            int count = productDAO.getTotalProductCount(color, categoryId, search);
            System.out.println(count);
            
            int endPage = count / 4;
            
            if(count % 4 != 0){
                endPage++;
            }
            for (ProductDTO productDTO : productList) {
                System.out.println(productDAO);
            }

            // Lấy danh sách màu sắc
            List<String> colors = productDAO.getAllColors();
            //Lấy danh sách category
            List<CategoryDTO> categoryList = cateDAO.getAllCategories();
            // Đưa dữ liệu vào request để truyền cho trang JSP
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("productList", productList);
            request.setAttribute("topProduct", topProduct);
            request.setAttribute("endPage", endPage);
            request.setAttribute("tag", index);
            request.setAttribute("search", search);
            request.setAttribute("color", color);
            request.setAttribute("categoryId", categoryId);
            request.setAttribute("sortField", sortField);
            request.setAttribute("isAscending", isAscending);
            request.setAttribute("colors", colors);

            // Chuyển hướng đến trang JSP để hiển thị dữ liệu
            request.getRequestDispatcher("homePage.jsp").forward(request, response);

        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
