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

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
    private UserService userService = new UserService();// 創建 UserService 物件


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        try {
            User user = userService.getUserByEmail(email);
            if (user != null) {
                response.getWriter().println("會員資料：" + user.getName() + ", " + user.getEmail() + ", " + user.getGender());
            } else {
                response.getWriter().println("查無此會員");
            }
        } catch (SQLException e) {
            throw new ServletException("資料庫錯誤", e);
        }
    }
}

