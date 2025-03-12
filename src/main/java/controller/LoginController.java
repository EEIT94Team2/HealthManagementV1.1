package controller;

import model.User;
import service.UserService;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/login") //串聯到URL到前端
public class LoginController extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userService.loginUser(email, password);
            if (user != null) {
            	// ✅ 設定 session，讓用戶登入後狀態可以保持
                request.getSession().setAttribute("user", user);
                //跳到會員中心
            	response.sendRedirect("/HealthManagement/jsp/membercenter.jsp");

            } else {
                response.sendRedirect("/HealthManagement/jsp/index.jsp?error=帳號或密碼錯誤");
            }
        } catch (SQLException e) {
            throw new ServletException("資料庫錯誤", e);
        }
    }
}

