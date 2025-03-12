package controller.social;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.social.SocialService;

import java.io.IOException;

@WebServlet("/api/Social/delete")
public class DeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SocialService socialService = new SocialService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int articleId = Integer.parseInt(request.getParameter("articleId"));
            socialService.deletePost(articleId);
            response.sendRedirect(request.getContextPath() + "/jsp/social/posts.jsp"); // ✅ 修改後的路徑
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "❌ 伺服器錯誤: 無法刪除文章");
        }
    }
}
