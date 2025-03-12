package controller.social;

import model.social.SocialPost;
import service.social.SocialService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/Social/search")
public class SearchController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SocialService socialService = new SocialService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("searchQuery");

        if (query == null || query.trim().isEmpty()) {
            request.setAttribute("errorMessage", "請輸入關鍵字進行查詢！");
        } else {
            List<SocialPost> searchResults = socialService.searchPosts(query);
            if (searchResults.isEmpty()) {
                request.setAttribute("errorMessage", "查無符合條件的文章！");
            }
            request.setAttribute("searchResults", searchResults);
        }

        request.getRequestDispatcher("/jsp/social/search.jsp").forward(request, response); // ✅ 修改後的路徑
    }
}

