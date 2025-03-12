<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>享健你，遇見更好的自己．</title>
    <!-- ✅ 引入 gymstyle.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/gymstyle.css">
<style>
input[type="text"], textarea {
    width: 30%;
    padding: 8px;
    font-size: 1em;
    border: 1px solid #ccc;
}
input[type="email"], textarea {
    width: 30%;
    padding: 8px;
    font-size: 1em;
    border: 1px solid #ccc;
}
input[type="password"], textarea {
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
    <h2>會員註冊</h2>
    <br>
    <form action="/HealthManagement/UserController" method="post">
    	
        <input type="hidden" name="action" value="register">
        姓名：<input type="text" name="name" required><br><br>
        Email：<input type="email" name="email" required><br><br>
        密碼：<input type="password" name="password" required><br><br>
        性別：
        <select name="gender">
            <option value="M">男</option>
            <option value="F">女</option>
            <option value="O">其他</option>
        </select><br><br>
        個人簡介：<textarea name="bio"></textarea><br><br>
        <button type="submit">註冊</button>
    </form>
    </div>
        <!-- ✅ 頁尾 -->
    <div id="footer">
        <p>&copy; 2025 享健你. 讓運動成為習慣，遇見更好的自己。</p>
    </div>
</body>
</html>


