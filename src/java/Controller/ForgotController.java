/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UsersDAO;
import Model.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author DLCT
 */
public class ForgotController extends HttpServlet {

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
            String action = request.getParameter("Forgot");
            UsersDAO dao = new UsersDAO();

            if ("sendUsername".equals(action)) {
                // Xử lý gửi email khi người dùng yêu cầu quên mật khẩu
                String username = request.getParameter("username");
                UserDTO user = dao.getByUsername(username);
                if (user != null) {
                    request.setAttribute("id", user.getId());
                    request.getRequestDispatcher("newPassword.jsp").forward(request, response);
                } else {
                    String msg = "USER NOT FOUND. PLEASE CHECK YOUR USERNAME";
                    request.setAttribute("error", msg);
                    request.getRequestDispatcher("forgot.jsp").forward(request, response);
                }

            } else if ("resetPassword".equals(action)) {
                String id = request.getParameter("id");
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");
                if (password.equals(confirmPassword)) {
                    boolean update = dao.updatePasswordById(id, password);
                    if (update) {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } else {
                    String msg = " PASSWORD AND PASSWORD CONFIRM NOT EQUAL";
                    request.setAttribute("error", msg);
                    request.setAttribute("id", id);
                    request.getRequestDispatcher("newPassword.jsp").forward(request, response);
                }
            } else {
                // Xử lý trường hợp mặc định, ví dụ: hiển thị trang quên mật khẩu
                request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);

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
