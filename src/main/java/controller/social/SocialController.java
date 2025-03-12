package controller.social;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.social.SocialPost;
import service.social.SocialService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/api/Social/post")
public class SocialController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SocialService socialService = new SocialService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SocialPost> posts = socialService.getAllPosts();
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("/jsp/social/posts.jsp").forward(request, response); // ✅ 修改後的路徑
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            int userId = 1; // 假設的 ID，可從 session 取得

            Date publishDate = new Date();
            socialService.addPost(new SocialPost(0, title, content, userId, publishDate));

            response.sendRedirect(request.getContextPath() + "/jsp/social/posts.jsp"); // ✅ 修改後的路徑
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "❌ 伺服器錯誤: 無法新增文章");
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        socialService.deletePost(articleId);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}

