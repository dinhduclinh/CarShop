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
import Model.CartDTO;
import Model.CartItem;
import Model.CategoryDTO;
import Model.OrderDTO;
import Model.OrderDetailDTO;
import Model.ProductDTO;
import Model.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author DLCT
 */
public class CartController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("cart");
            ProductDAO proDao = new ProductDAO();
            CategoryDAO cateDAO = new CategoryDAO();

            String indexParam = request.getParameter("index");
            int index = (indexParam != null && !indexParam.isEmpty()) ? Integer.parseInt(indexParam) : 1;
            String color = request.getParameter("color");
            String search = request.getParameter("search");
            String categoryId = request.getParameter("categoryId");
            String sortField = request.getParameter("sortField");
            String sortDirection = request.getParameter("sortDirection");
            boolean isAscending = (sortDirection != null && sortDirection.equalsIgnoreCase("asc"));

            // Lấy danh sách sản phẩm theo các tham số
            List<ProductDTO> productList = proDao.getProducts(index, color, categoryId, sortField, isAscending, "1", search);

            // Lấy tổng số lượng sản phẩm (để phục vụ việc phân trang)
            int count = proDao.getTotalProductCount(color, categoryId, search);

            int endPage = count / 4;

            if (count % 4 != 0) {
                endPage++;
            }

            // Lấy danh sách màu sắc
            List<String> colors = proDao.getAllColors();
            if (cart == null) {
                cart = new CartDTO();
            }

            String action = request.getParameter("action");

            if ("add".equals(action)) {
                // Thêm sản phẩm vào giỏ hàng
                String productId = request.getParameter("productId");
                int id = Integer.parseInt(productId);
                ProductDTO product = proDao.getProductById(id);
                int quantity = (request.getParameter("quantity") != null && !request.getParameter("quantity").isEmpty()) ? Integer.parseInt(request.getParameter("quantity")) : 1;
                // Kiểm tra xem sản phẩm có trong giỏ hàng chưa
                CartItem existingItem = cart.findItemByProduct(product);
                if (existingItem != null) {
                    // Nếu sản phẩm đã có trong giỏ hàng, tăng quantity
                    existingItem.setQuantity(existingItem.getQuantity() + quantity);
                } else {
                    // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
                    cart.addToCart(product, quantity);
                }
                session.setAttribute("cart", cart);
                //Lấy danh sách category
                List<CategoryDTO> categoryList = cateDAO.getAllCategories();
                // Đưa dữ liệu vào request để truyền cho trang JSP
                request.setAttribute("categoryList", categoryList);
                request.setAttribute("productList", productList);
                request.setAttribute("endPage", endPage);
                request.setAttribute("tag", index);
                request.setAttribute("color", color);
                request.setAttribute("categoryId", categoryId);
                request.setAttribute("sortField", sortField);
                request.setAttribute("isAscending", isAscending);
                request.setAttribute("colors", colors);
                request.setAttribute("cartcheck", "cartcheck");
                // Chuyển hướng đến trang JSP để hiển thị dữ liệu
                request.getRequestDispatcher("HomePageController").forward(request, response);
            } else if ("delete".equals(action)) {
                // Xóa sản phẩm khỏi giỏ hàng
                String productId = request.getParameter("productId");
                int id = Integer.parseInt(productId);
                ProductDTO product = proDao.getProductById(id);

                cart.removeFromCart(product);
                session.setAttribute("cart", cart);
                response.sendRedirect("cartDetail.jsp");
            } else if ("update".equals(action)) {
                // Cập nhật số lượng sản phẩm trong giỏ hàng
                String productId = request.getParameter("productId");
                int id = Integer.parseInt(productId);
                ProductDTO product = proDao.getProductById(id);

                String newQuantityStr = request.getParameter("newQuantity");
                int newQuantity = 0;

                try {
                    newQuantity = Integer.parseInt(newQuantityStr);
                } catch (NumberFormatException e) {
                    // Xử lý nếu giá trị mới của số lượng không phải là một số nguyên
                    // Có thể thông báo lỗi hoặc thực hiện xử lý khác tùy thuộc vào yêu cầu của bạn
                    e.printStackTrace();
                }

                // Kiểm tra xem số lượng mới có hợp lệ không
                if (newQuantity > 0) {
                    cart.updateQuantity(product, newQuantity);

                } else {
                    cart.removeFromCart(product);
                }
                session.setAttribute("cart", cart);
                // Redirect hoặc chuyển hướng đến trang cần thiết
                response.sendRedirect("cartDetail.jsp");
            } else if ("checkcout".equals(action)) {
                cart = (CartDTO) session.getAttribute("cart");
                OrderDAO oDao = new OrderDAO();
                UserDTO user = (UserDTO) session.getAttribute("account");
                ProductDAO prodao = new ProductDAO();
                // Kiểm tra xem người dùng đã có đơn hàng chưa
                boolean hasExistingOrder = oDao.checkExistingOrder(user.getId());
                if (cart != null && !cart.getCartItems().isEmpty()) {

                    // Lấy id của đơn hàng mới nhất của người dùng
                    int latestOrderId = oDao.getLatestOrderId(user.getId());
                    if (hasExistingOrder) {//tra ve false neu hoan thanh
                        // Nếu đơn hàng mới nhất đã hoàn thành, tạo đơn hàng mới
                        OrderDTO newOrder = new OrderDTO();
                        newOrder.setUserId(user.getId());
                        newOrder.setStatus(true); // Đánh dấu là đơn hàng chưa hoàn thành

                        // Thực hiện chèn đơn hàng mới và nhận lại OrderDTO với id đã được cập nhật
                        newOrder = oDao.insertOrder(newOrder);

                        // Kiểm tra xem việc chèn đơn hàng mới thành công hay không
                        if (newOrder != null) {
                            // Thêm thông tin từng sản phẩm từ giỏ hàng vào bảng OrderDetail
                            OrderDetailDAO odDao = new OrderDetailDAO();
                            for (CartItem cartItem : cart.getCartItems()) {
                                OrderDetailDTO orderDetail = new OrderDetailDTO();
                                orderDetail.setQuantity(cartItem.getQuantity());
                                orderDetail.setPrice(cartItem.getProduct().getPrice());
                                orderDetail.setOrderId(newOrder.getId()); // Sử dụng id của đơn hàng vừa tạo
                                orderDetail.setProductId(cartItem.getProduct().getId());
                                orderDetail.setStatus(true); // Assuming you want to set the status to true

                                //update stock
                                ProductDTO product = prodao.getProductById(cartItem.getProduct().getId());
                                int newStock = product.getStock() - cartItem.getQuantity();
                                proDao.updateStockByProductId(cartItem.getProduct().getId(), newStock);
                                // Insert into OrderDetail table
                                odDao.insertOrderDetail(orderDetail);
                                oDao.updateStatus(newOrder.getId(), false); //cap nhap lai order hoan 
                            }
                        }
                    } else {
                        // Nếu đơn hàng mới nhất chưa hoàn thành, sử dụng đơn hàng đó
                        // Thêm thông tin từng sản phẩm từ giỏ hàng vào bảng OrderDetail
                        OrderDetailDAO odDao = new OrderDetailDAO();
                        for (CartItem cartItem : cart.getCartItems()) {
                            OrderDetailDTO orderDetail = new OrderDetailDTO();
                            orderDetail.setQuantity(cartItem.getQuantity());
                            orderDetail.setPrice(cartItem.getProduct().getPrice());

                            if (latestOrderId > 0) {
                                orderDetail.setOrderId(latestOrderId);
                            } else {
                                // If latestOrderId is not valid, create a new order
                                OrderDTO newOrder2 = new OrderDTO();
                                newOrder2.setUserId(user.getId());
                                newOrder2.setStatus(true); // Đánh dấu là đơn hàng chưa hoàn thành

                                // Thực hiện chèn đơn hàng mới và nhận lại OrderDTO với id đã được cập nhật
                                newOrder2 = oDao.insertOrder(newOrder2);
                                latestOrderId = newOrder2.getId();
                                orderDetail.setOrderId(latestOrderId);// Cập nhật latestOrderId với id mới tạo
                            }
                            // Sử dụng id của đơn hàng mới nhất
                            orderDetail.setProductId(cartItem.getProduct().getId());
                            orderDetail.setStatus(true); // Assuming you want to set the status to true
                            //update stock
                            ProductDTO product = prodao.getProductById(cartItem.getProduct().getId());
                            int newStock = product.getStock() - cartItem.getQuantity();
                            proDao.updateStockByProductId(cartItem.getProduct().getId(), newStock);
                            // Insert into OrderDetail table
                            odDao.insertOrderDetail(orderDetail);
                            oDao.updateStatus(latestOrderId, false);//cap nhap lai order hoan 
                        }
                    }

                    // Sau khi lưu thành công, bạn có thể xóa giỏ hàng
                    cart.clearCart();
                    session.setAttribute("cart", cart);

                    // Redirect hoặc forward tới trang kết quả hoặc trang cảm ơn
                    request.getRequestDispatcher("HomePageController").forward(request, response);
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
