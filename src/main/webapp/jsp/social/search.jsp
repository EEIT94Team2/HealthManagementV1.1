<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, model.social.SocialPost" %>

<%
    List<SocialPost> searchResults = (List<SocialPost>) request.getAttribute("searchResults");
    String errorMessage = (String) request.getAttribute("errorMessage");
    boolean searched = (searchResults != null || errorMessage != null); // 判斷是否有進行過查詢
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>搜尋文章</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/gymstyle.css">
        <style>
        /* 🔹 讓「回到論壇」按鈕滑鼠移上去變成手指 */
        .search-button {
            display: inline-block;
            background-color: #f0f0f0;
            color: #333;
            padding: 8px 12px;
            border: 1px solid #ccc;
            border-radius: 5px;
            text-decoration: none;
            font-size: 14px;
            cursor: pointer; /* ✅ 讓滑鼠變成手指 */
        }

        .search-button:hover {
            background-color: #e3e3e3;
        }
    </style>
        <script>
        // 轉跳到論壇並隱藏「回到論壇」按鈕
        function goToForum() {
            window.location.href = "${pageContext.request.contextPath}/jsp/social/posts.jsp";
        }
    </script>
</head>
<body>

    <jsp:include page="header.jsp" />

    <div id="content">
        <h1>搜尋文章</h1>

        <!-- 搜尋表單 -->
        <form action="${pageContext.request.contextPath}/api/Social/search" method="get">
            <label>關鍵字：</label>
            <input type="text" name="searchQuery" required>
            <button type="submit" class="search-button">查詢</button>
           
        </form>

        <hr>

        <!-- 顯示錯誤訊息 -->
        <% if (errorMessage != null) { %>
            <p style="color: red;"><%= errorMessage %></p>
        <% } %>

        <!-- 只有在用戶點擊查詢後，且 `searchResults` 為空時，才顯示 `查無相關文章` -->
        <% if (searched && (searchResults == null || searchResults.isEmpty())) { %>
            <p style="color: gray;"> </p>
        <% } %>

        <!-- 顯示查詢結果 -->
        <% if (searched) { %> <!-- 只有當查詢後才顯示 -->
            <% if (searchResults != null && !searchResults.isEmpty()) { %>
                <ul id="postList">
                    <% for (SocialPost post : searchResults) { 
                        String content = post.getContent();
                        boolean isLongContent = content.length() > 50;
                        String previewText = isLongContent ? content.substring(0, 50).trim() : content;
                    %>
                        <li class="post-item" data-id="<%= post.getArticleId() %>">

                        <!-- 🔹 文章標題 -->
                        <h3>
                            <a href="${pageContext.request.contextPath}/jsp/social/postDetail.jsp?articleId=<%= post.getArticleId() %>">
                                <%= post.getTitle() %>
                            </a>
                        </h3>

                        <!-- 🔹 文章預覽 (最多 50 個字) -->
                        <p class="preview">
                            <%= previewText %><% if (isLongContent) { %><span class="ellipsis">...</span><a href="${pageContext.request.contextPath}/jsp/social/postDetail.jsp?articleId=<%= post.getArticleId() %>" class="read-more">閱讀更多</a><% } %>
                        </p>

                        <!-- 🔹 文章資訊 -->
                        <div class="post-meta">
                            <img src="${pageContext.request.contextPath}/images/user.svg" alt="User" width="16px" height="16px"> <%= post.getUserId() %>
                            <img src="${pageContext.request.contextPath}/images/clock.svg" alt="Clock" width="16px" height="16px"> <%= post.getFormattedPublishDate() %>
                        </div>
                    </li>
                <% } %>
            </ul>
        <% } %>
            <!-- 🔹 查詢後才顯示「回到論壇」按鈕 -->
            <div class="buttons-container">
                <button onclick="goToForum()" class="search-button">回到論壇</button>
                
            </div>
                <div id="footer">
       			 <p>&copy; 2025 享健你. 讓運動成為習慣，遇見更好的自己。</p>
    </div>
            <% } %> <!-- ✅ 正確關閉 `if (searched)` -->
    </div>

</body>
</html>
