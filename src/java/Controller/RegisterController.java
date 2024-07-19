/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsersDAO;
import Model.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author DLCT
 */
public class RegisterController extends HttpServlet {

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
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            int role = 2;//user
            UsersDAO userDAO = new UsersDAO();
            boolean usernameExists = userDAO.checkByUsername(username);
            // Validate input data (you can add more validation logic)
            if (username == null || password == null || fullname == null || address == null || phone == null
                    || username.isEmpty() || password.isEmpty() || fullname.isEmpty() || address.isEmpty()
                    || phone.isEmpty()) {
                request.setAttribute("error", "Please fill in all fields.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);
                return;
            }
            if (usernameExists) {
                // Tên người dùng đã tồn tại
                // Thực hiện hành động tương ứng, ví dụ: hiển thị thông báo lỗi
                request.setAttribute("error", "Username already exists. Please choose a different one.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);
            } else {

                // Create a UserDTO object
                UserDTO newUser = new UserDTO();
                newUser.setRoleId(role);
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setFullname(fullname);
                newUser.setAddress(address);
                newUser.setPhone(phone);

                // Use UserDAO to add the new user to the database
                boolean registrationSuccess = userDAO.insertUser(newUser);

                if (registrationSuccess) {
                    // Registration successful, redirect to login page
                    response.sendRedirect("login.jsp");
                } else {
                    // Registration failed, display error message
                    request.setAttribute("error", "Registration failed. Please try again.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                    dispatcher.forward(request, response);
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
