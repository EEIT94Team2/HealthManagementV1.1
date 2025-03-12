<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>享健你，遇見更好的自己．</title>
    <!-- ✅ 引入 gymstyle.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/gymstyle.css">
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
            <li><a href="http://localhost:8080/HealthManagement/jsp/manage.jsp"  class="active">會員管理</a></li>
            <li><a href="http://localhost:8080/HealthManagement/product.html">商城購物</a></li>
            <li><a href="http://localhost:8080/HealthManagement/jsp/fitness/index.jsp">健身成效</a></li>
            <li><a href="http://localhost:8080/HealthManagement/jsp/course/index.jsp">課程管理</a></li>
            <li><a href="http://localhost:8080/HealthManagement/api/Social/post">社群論壇</a></li>
		</ul>
    </div>
    <div id="content" align="center">
    <br><br>
    <h2>註冊成功！</h2>
    <br>
    <a href="manage.jsp">回會員管理</a>
    </div>
        <!-- ✅ 頁尾 -->
    <div id="footer">
        <p>&copy; 2025 享健你. 讓運動成為習慣，遇見更好的自己。</p>
    </div>
</body>
</html>