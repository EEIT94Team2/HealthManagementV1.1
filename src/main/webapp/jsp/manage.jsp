<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, model.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>享健你，遇見更好的自己．</title>
    <!-- ✅ 引入 gymstyle.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/gymstyle.css">
<style>
hr {
    border: 1px solid #ccc;  /* 設置邊框顏色 */
    margin: 20px 0;  /* 上下間距 */
}
input[type="email"], textarea {
    width: 30%;
    padding: 8px;
    font-size: 1em;
    border: 1px solid #ccc;
}
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
button[type="button"] {
    background-color: #808080;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
}
button[type="button"]:hover {
    background-color: #c0c0c0;
}
</style>
</head>
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
            <li><a href="http://localhost:8080/HealthManagement/jsp/membercenter.jsp"  class="active">會員管理</a></li>
            <li><a href="http://localhost:8080/HealthManagement/product.html">商城購物</a></li>
            <li><a href="http://localhost:8080/HealthManagement/jsp/fitness/index.jsp">健身成效</a></li>
            <li><a href="http://localhost:8080/HealthManagement/jsp/course/index.jsp">課程管理</a></li>
            <li><a href="http://localhost:8080/HealthManagement/api/Social/post">社群論壇</a></li>
        </ul>
		</div>
    <div id="content" align="center"><br>
    <h2>會員管理</h2>
	<hr>

    <!-- 刪除會員 -->
    <h3>刪除會員</h3><br>
    <form action="${pageContext.request.contextPath}/UserController" method="post">
        <input type="hidden" name="action" value="delete">
        Email：<input type="email" name="email" required>
        <button type="submit">刪除</button>
    </form>
	<hr>

    <!-- 查詢會員 -->
    <h3>查詢所有會員</h3><br>
    <form action="${pageContext.request.contextPath}/UserController" method="get">
        <input type="hidden" name="action" value="list">
        <button type="submit">顯示所有會員</button>
    </form><br>

 	 <h3>查詢特定會員</h3><br>
	 <form action="${pageContext.request.contextPath}/UserController" method="post">
   		 <input type="hidden" name="action" value="search"> <!-- ✅ 確保 action 被傳遞 -->
   	Email：<input type="email" name="email" required>
    		<button type="submit">查詢</button>
	</form>


    <hr>

    <!-- ✅ 會員列表 -->
   <h3>會員列表</h3>
<table border="1">
    <tr>
        <th>姓名</th>
        <th>Email</th>
        <th>性別</th>
        <th>簡介</th>
    </tr>
    <% 
        List<User> users = (List<User>) request.getAttribute("users");
        User searchedUser = (User) request.getAttribute("searchedUser");

        if (searchedUser != null) { // ✅ 只顯示查詢的特定會員
    %>
        <tr style="background-color: #D0D0D0;"> <!-- 高亮顯示查詢的會員 -->
            <td><%= searchedUser.getName() %></td>
            <td><%= searchedUser.getEmail() %></td>
            <td><%= searchedUser.getGender() %></td>
            <td><%= searchedUser.getBio() %></td>
        </tr>
    <% } else if (users != null && !users.isEmpty()) { 
        for (User user : users) { 
    %>
        <tr>
            <td><%= user.getName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getGender() %></td>
            <td><%= user.getBio() %></td>
        </tr>
    <% 
        } 
    } else { 
    %>
        <tr><td colspan="4">目前沒有會員資料</td></tr>
    <% } %>
</table>

	<hr>
    <!-- 返回會員中心 -->
    <a href="${pageContext.request.contextPath}/jsp/membercenter.jsp">
        <button type="button">返回會員中心</button>
    </a>
</div>
        <!-- ✅ 頁尾 -->
    <div id="footer">
        <p>&copy; 2025 享健你. 讓運動成為習慣，遇見更好的自己。</p>
    </div>
</body>
</html>