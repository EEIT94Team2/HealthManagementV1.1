package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//清除session 回到首頁
@WebServlet("/LogoutController") //串聯到URL到前端
public class LogoutController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // 不建立新 session
        if (session != null) {
            session.invalidate(); // ✅ 清除 session
        }
        response.sendRedirect("jsp/index.jsp"); // ✅ 回到首頁
    }
}
