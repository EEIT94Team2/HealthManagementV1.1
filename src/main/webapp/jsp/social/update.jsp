<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String articleId = request.getParameter("articleId");
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    boolean showUpdateForm = request.getParameter("showUpdate") != null;
%>

<!-- 修改文章按鈕 -->
<a href="?showUpdate=true&articleId=<%= articleId %>&title=<%= title %>&content=<%= content %>">修改</a>
<jsp:include page="header.jsp" />
<% if (showUpdateForm) { %>
    <div>
        <h2>修改文章</h2>
        <form action="${pageContext.request.contextPath}/api/Social/update" method="post">
            <input type="hidden" name="articleId" value="<%= articleId %>">
            <label>主題：</label>
            <input type="text" name="title" value="<%= title %>" required><br>
            <label>內容：</label>
            <textarea name="content" required><%= content %></textarea><br>
            <button type="submit">確認</button>
            <a href="posts.jsp">取消</a>
        </form>
    </div>
<% } %>
