/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CategoryDAO;
import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import DAO.ProductDAO;
import DAO.UsersDAO;
import Model.CategoryDTO;
import Model.OrderDTO;
import Model.OrderDetailDTO;
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
public class ManageProductController extends HttpServlet {

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
            String ActionName = request.getParameter("Product");
            ProductDAO productDAO = new ProductDAO();
            CategoryDAO cateDAO = new CategoryDAO();

            String indexParam = request.getParameter("index");
            int index = (indexParam != null && !indexParam.isEmpty()) ? Integer.parseInt(indexParam) : 1;
            String color = request.getParameter("color");
            String categoryId = request.getParameter("categoryId");
            String sortField = request.getParameter("sortField");
            String sortDirectionParam = request.getParameter("sortDirection");
            boolean isAscending = (sortDirectionParam != null && sortDirectionParam.equalsIgnoreCase("asc"));
            String search = request.getParameter("search");
            String status = "1";
            // Lấy danh sách sản phẩm theo các tham số
            List<ProductDTO> productList = productDAO.getProducts(index, color, categoryId, sortField, isAscending, status, search);
            // Lấy tổng số lượng sản phẩm (để phục vụ việc phân trang)
            int count = productDAO.getTotalProductCount(color, categoryId, search);

            int endPage = count / 4;

            if (count % 4 != 0) {
                endPage++;
            }

            // Lấy danh sách màu sắc
            List<String> colors = productDAO.getAllColors();

            //Lấy danh sách category
            List<CategoryDTO> categoryList = cateDAO.getAllCategories();

            //create
            if (ActionName != null) {
                if (ActionName.equalsIgnoreCase("SendAddProduct")) {
                    request.setAttribute("categoryList", categoryList);
                    request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                }

                if (ActionName.equalsIgnoreCase("SendEditProduct")) {
                    String id = request.getParameter("id");
                    int productId = Integer.parseInt(id);
                    ProductDTO product = productDAO.getProductById(productId);
                    CategoryDTO category = cateDAO.getCategoryById(product.getCategoryId());
                    request.setAttribute("category", category);
                    request.setAttribute("product", product);

                    request.setAttribute("categoryList", categoryList);
                    request.getRequestDispatcher("editProduct.jsp").forward(request, response);
                }
                if (ActionName.equalsIgnoreCase("Create")) {
                    String name = request.getParameter("name");
                    String priceStr = request.getParameter("price");
                    String title = request.getParameter("title");
                    String colorAdd = request.getParameter("colorAdd");
                    String image = request.getParameter("image");
                    String stockStr = request.getParameter("stock");
                    String description = request.getParameter("description");
                    String categoryIdAdd = request.getParameter("categoryIdAdd");

                    // Chuyển đổi giá trị chuỗi thành kiểu dữ liệu phù hợp
                    double price = Double.parseDouble(priceStr);
                    int stock = Integer.parseInt(stockStr);

                    ProductDTO product = new ProductDTO();
                    product.setName(name);
                    product.setPrice(price);
                    product.setTitle(title);
                    product.setColor(colorAdd);
                    product.setImage(image);
                    product.setStock(stock);
                    product.setDescription(description);
                    product.setCategoryId(Integer.parseInt(categoryIdAdd));
                    // Gọi phương thức thêm sản phẩm từ ProductDAO
                    productDAO.insertProduct(product);
                    //chuyển trang sau khi add
                    int countAfterDelete = productDAO.getTotalProductCount(color, categoryId, search);
                    int endPageAfterChange = countAfterDelete / 4;

                    if (countAfterDelete % 4 != 0) {
                        endPageAfterChange++;
                    }
                    List<ProductDTO> productListAfterUpdate = productDAO.getProducts(index, color, categoryId, sortField, isAscending, status, search);
                    request.setAttribute("categoryList", categoryList);
                    request.setAttribute("productList", productListAfterUpdate);
                    request.setAttribute("endPage", endPageAfterChange);
                    request.setAttribute("tag", index);
                    request.setAttribute("color", color);
                    request.setAttribute("categoryId", categoryId);
                    request.setAttribute("sortField", sortField);
                    request.setAttribute("colors", colors);
                    request.getRequestDispatcher("manageProduct.jsp").forward(request, response);

                }
                //delete
                if (ActionName.equalsIgnoreCase("Delete")) {
                    String id = request.getParameter("id");
                    int parseId = Integer.parseInt(id);
                    productDAO.deleteById(parseId);

                    //chuyển trang sau khi xóa
                    int countAfterDelete = productDAO.getTotalProductCount(color, categoryId, search);
                    int endPageAfterChange = countAfterDelete / 4;

                    if (countAfterDelete % 4 != 0) {
                        endPageAfterChange++;
                    }
                    List<ProductDTO> productListAfterUpdate = productDAO.getProducts(index, color, categoryId, sortField, isAscending, status, search);
                    request.setAttribute("categoryList", categoryList);
                    request.setAttribute("productList", productListAfterUpdate);
                    request.setAttribute("endPage", endPageAfterChange);
                    request.setAttribute("tag", index);
                    request.setAttribute("color", color);
                    request.setAttribute("categoryId", categoryId);
                    request.setAttribute("sortField", sortField);
                    request.setAttribute("colors", colors);

                    request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
                }
                //edit
                if (ActionName.equalsIgnoreCase("Edit")) {
                    String id = request.getParameter("id");
                    int productId = Integer.parseInt(id);
                    System.out.println(productId);
                    String name = request.getParameter("name");
                    String priceStr = request.getParameter("price");
                    String title = request.getParameter("title");
                    String colorAdd = request.getParameter("colorAdd");
                    String image = request.getParameter("image");
                    String stockStr = request.getParameter("stock");
                    String description = request.getParameter("description");
                    String categoryIdAdd = request.getParameter("categoryIdAdd");

                    // Chuyển đổi giá trị chuỗi thành kiểu dữ liệu phù hợp
                    double price = Double.parseDouble(priceStr);
                    int stock = Integer.parseInt(stockStr);
                    ProductDTO product = new ProductDTO();
                    product.setId(productId);
                    product.setName(name);
                    product.setPrice(price);
                    product.setTitle(title);
                    product.setColor(colorAdd);
                    product.setImage(image);
                    product.setStock(stock);
                    product.setDescription(description);
                    product.setCategoryId(Integer.parseInt(categoryIdAdd));
                    productDAO.updateProduct(product);

                    //chuyển trang sau khi update 
                    List<ProductDTO> productListAfterUpdate = productDAO.getProducts(index, color, categoryId, sortField, isAscending, status, search);
                    request.setAttribute("categoryList", categoryList);
                    request.setAttribute("productList", productListAfterUpdate);
                    request.setAttribute("endPage", endPage);
                    request.setAttribute("tag", index);
                    request.setAttribute("color", color);
                    request.setAttribute("categoryId", categoryId);
                    request.setAttribute("sortField", sortField);
                    request.setAttribute("colors", colors);

                    request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
                }
                if (ActionName.equalsIgnoreCase("Manage Product")) {
                    // Đưa dữ liệu vào request để truyền cho trang JSP
                    request.setAttribute("categoryList", categoryList);
                    request.setAttribute("productList", productList);
                    request.setAttribute("endPage", endPage);
                    request.setAttribute("tag", index);
                    request.setAttribute("color", color);
                    request.setAttribute("categoryId", categoryId);
                    request.setAttribute("sortField", sortField);
                    request.setAttribute("colors", colors);

                    request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
                }
                if (ActionName.equalsIgnoreCase("Revenue")) {
                    OrderDetailDAO odDao = new OrderDetailDAO();
                    String indexRevenue = request.getParameter("indexRevenue");
                    int indexRevenueParse = (indexRevenue != null && !indexRevenue.isEmpty()) ? Integer.parseInt(indexRevenue) : 1;
                    OrderDAO oDao = new OrderDAO();
                    OrderDetailDAO od = new OrderDetailDAO();
                    int count1 = oDao.getTotalOrderCount();
                    UsersDAO us = new UsersDAO();
                    int endPage1 = count1 / 4;

                    if (count1 % 4 != 0) {
                        endPage1++;
                    }
                    List<OrderDTO> listOrder = oDao.getOrders(indexRevenueParse);
                    for (OrderDTO orderDTO : listOrder) {
                        System.out.println(orderDTO);
                    }
                    request.setAttribute("od", od);
                    request.setAttribute("us", us);
                    request.setAttribute("endPage", endPage1);
                    request.setAttribute("indexRevenue", indexRevenueParse);
                    request.setAttribute("orderList", listOrder);
                    request.setAttribute("allquantity", odDao.getAllQuantity());
                    request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                }

            }
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
