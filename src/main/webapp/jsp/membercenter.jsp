<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
    // 確保使用者已登入
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>享健你，遇見更好的自己．</title>
    <!-- ✅ 引入 gymstyle.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/gymstyle.css">
</head>
<style>
button[type="submit"] {
    background-color: #808080;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
}
button[type="submit"]:hover {
    background-color: #c0c0c0;
}
</style>
<body>
    <!-- ✅ 標題區 -->
    <div id="header">
        <h1>享健你，遇見更好的自己．</h1>
        <h2>你，今天健了嗎？</h2>
    </div>
    <!-- ✅ 導覽列 -->
    <div id="navigation">
		<ul>
            <li><a href="http://localhost:8080/HealthManagement/jsp/course/HealthManagement.jsp" >首頁</a></li>
            <li><a href="http://localhost:8080/HealthManagement/jsp/manage.jsp"  class="active">會員管理</a></li>
            <li><a href="http://localhost:8080/HealthManagement/product.html">商城購物</a></li>
            <li><a href="http://localhost:8080/HealthManagement/jsp/fitness/index.jsp">健身成效</a></li>
            <li><a href="http://localhost:8080/HealthManagement/jsp/course/index.jsp">課程管理</a></li>
            <li><a href="http://localhost:8080/HealthManagement/api/Social/post">社群論壇</a></li> <!-- ✅ 修正網址 -->
		</ul>
    </div>

    <div id="content" align="center"><br>
	<h2>歡迎, <%= user.getName() %>！這裡是你的會員中心</h2>
	<br>
	<div class="button-container">
        <form action="/HealthManagement/LogoutController" method="post">
            <button type="submit">登出</button>
        </form>
    </div>
    </div>
        <!-- ✅ 頁尾 -->
    <div id="footer">
        <p>&copy; 2025 享健你. 讓運動成為習慣，遇見更好的自己。</p>
    </div>
</body>
</html>

