<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
    // 檢查 session，判斷用戶是否已經登入
    User user = (User) session.getAttribute("user");
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
input[type="password"], textarea {
    width: 50%;
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
<body>

    <!-- ✅ 標題區 -->
    <div id="header">
        <h1>享健你，遇見更好的自己．</h1>
        <h2>你，今天健了嗎？</h2>
    </div>

    <!-- ✅ 導覽列 -->
    <div id="navigation">
		<ul>
			<li><a href="http://localhost:8080/HealthManagement/jsp/course/HealthManagement.jsp"  class="active">首頁</a></li>
			<li><a href="http://localhost:8080/HealthManagement/jsp/membercenter.jsp">會員管理</a></li>
			<li><a href="http://localhost:8080/HealthManagement/product.html">商城購物</a></li>
			<li><a href="http://localhost:8080/HealthManagement/jsp/fitness/index.jsp">健身成效</a></li>
			<li><a href="http://localhost:8080/HealthManagement/jsp/course/index.jsp">課程管理</a></li>
            <li><a href="http://localhost:8080/HealthManagement/api/Social/post">社群論壇</a></li> <!-- ✅ 修正網址 -->
		</ul>
    </div>

    <!-- ✅ 內容區 -->
    <div id="content" align="center">
        <h1>會員登入</h1>

        <div class="container">
            <% if (user == null) { %>
            <!-- ✅ 登入表單 -->
            <form action="/HealthManagement/api/login" method="post"><br>
                <input type="text" name="email" placeholder="輸入 Email" required><br><br>
                <input type="password" name="password" placeholder="輸入密碼" required><br>

                <!-- ✅ 讓「登入」與「註冊」按鈕並排顯示 -->
                <div class="button-container"><br>
                    <button type="submit">登入</button>
                    <button type="button" onclick="location.href='register.jsp'">註冊</button>
                </div>
            </form>
            <% } else { %><br>
            <!-- ✅ 如果用戶已登入，顯示會員中心按鈕 -->
            <h2>歡迎, <%= user.getName() %>！</h2><br>
            <form action="${pageContext.request.contextPath}/jsp/membercenter.jsp">
                <button type="submit">進入會員中心</button>
            </form>
            <% } %>
        </div>
    </div>

    <!-- ✅ 頁尾 -->
    <div id="footer">
        <p>&copy; 2025 享健你. 讓運動成為習慣，遇見更好的自己。</p>
    </div>

</body>

<!-- ✅ JavaScript 讓導航列的選單高亮 -->
<script>
    // 選擇所有的 <a> 元素
    const links = document.querySelectorAll("#navigation a");

    // 為每個 <a> 元素添加點擊事件
    links.forEach(link => {
        link.addEventListener("click", function(event) {
            // 移除所有其他 <a> 元素的 'active' 類別
            links.forEach(link => link.classList.remove("active"));

            // 為當前點擊的 <a> 元素添加 'active' 類別
            this.classList.add("active");
        });
    });
</script>
</html>


