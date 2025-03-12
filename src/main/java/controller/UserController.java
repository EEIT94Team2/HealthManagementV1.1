package controller;

import model.User;
import service.UserService;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserController")//匹配JSP表單的 
public class UserController extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("register".equals(action)) {
                registerUser(request, response);
            } else if ("update".equals(action)) {
                updateUser(request, response);
            } else if ("delete".equals(action)) {
                deleteUser(request, response);
            } else if ("search".equals(action)) {  // ✅ 處理單一會員查詢
                searchUser(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
            if ("list".equals(request.getParameter("action"))) {
                listUsers(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        char gender = request.getParameter("gender").charAt(0);
        String bio = request.getParameter("bio");

        boolean success = userService.registerUser(name, email, password, gender, bio);
        if (success) {
        	response.sendRedirect("/HealthManagement/jsp/success.jsp");
        } else {
            response.sendRedirect("register.jsp?error=Email已存在");
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        User user = new User(
            0, request.getParameter("name"),
            request.getParameter("email"),
            request.getParameter("password"),
            request.getParameter("gender").charAt(0),
            request.getParameter("bio")
        );
        userService.updateUser(user);
        response.sendRedirect("/jsp/manage.jsp");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        userService.deleteUser(request.getParameter("email"));
        response.sendRedirect(request.getContextPath() + "/jsp/successdelete.jsp");
        }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<User> users = userService.getAllUsers();
        //把 會員資料 (users) 放進 request。
        request.setAttribute("users", users);
        // 找到manage.jsp                          轉交請求給 manage.jsp
        request.getRequestDispatcher("/jsp/manage.jsp").forward(request, response);
        //getRequestDispatcher() 是相對於 webapp去找
    }
    
    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String email = request.getParameter("email");  // 從請求中獲取 email
        User searchedUser = userService.getUserByEmail(email);  // 查詢特定會員

        request.setAttribute("searchedUser", searchedUser);  // 只傳特定會員，不傳所有會員

        request.getRequestDispatcher("/jsp/manage.jsp").forward(request, response);  // 轉發到 manage.jsp
    }


}
