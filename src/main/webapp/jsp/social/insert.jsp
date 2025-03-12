<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 發表文章按鈕 -->
<a href="?showInsert=true">發表文章</a>
<jsp:include page="header.jsp" />
<%
    boolean showInsertForm = "true".equals(request.getParameter("showInsert"));
    if (showInsertForm) {
%>
    <div>
        <h2>發表文章</h2>
        <form action="${pageContext.request.contextPath}/api/Social/post" method="post">
            <label>主題：</label>
            <input type="text" name="title" required><br>
            <label>內容：</label>
            <textarea name="content" required></textarea><br>
            <button type="submit">提交</button>
            <a href="posts.jsp">取消</a>
        </form>
    </div>
<% } %>
