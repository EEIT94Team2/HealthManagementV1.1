<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>享健你，遇見更好的自己．</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/gymstyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <div id="header">
        <h1>享健你，遇見更好的自己．</h1>
        <h2>你，今天健了嗎？</h2>
    </div>
    <div id="navigation">
        <ul>
            <li><a href="http://localhost:8080/HealthManagement/jsp/course/HealthManagement.jsp">首頁</a></li>
            <li><a href="http://localhost:8080/HealthManagement/jsp/membercenter.jsp" >會員管理</a></li>
            <li><a href="http://localhost:8080/HealthManagement/product.html">商城購物</a></li>
            <li><a href="http://localhost:8080/HealthManagement/jsp/fitness/index.jsp">健身成效</a></li>
            <li><a href="http://localhost:8080/HealthManagement/jsp/course/index.jsp" class="active">課程管理</a></li>
            <li><a href="http://localhost:8080/HealthManagement/api/Social/post">社群論壇</a></li>
        </ul>
    </div>
    <br>
	<div id="content" align="center">
        <h2>查詢課程資訊</h2>
        <form method="post" action="${pageContext.request.contextPath}/CourseDAO2">
            <input type="hidden" name="action" value="find">
            <p>
                請輸入課程編號：<input type="text" name="course_id" required />
                <input type="submit" value="確定" />
            </p>
            <div>
                <% String errorMessage = (String) request.getAttribute("errorMessage"); 
                    if (errorMessage != null) { %>
                    <br><div class="error-message" style="color: red;">
                        <%= errorMessage %>
                    </div>
                <% } %>
            </div>
            <table border="1">
                <tr>
                    <td>課程編號：</td>
                    <td><input type="text" disabled value="${course.course_id}"></td>
                </tr>
                <tr>
                    <td>課程名稱：</td>
                    <td><input type="text" disabled value="${course.course_name}"></td>
                </tr>
                <tr>
                    <td>課程描述：</td>
                    <td><textarea disabled rows="5" style="overflow: hidden; resize: none;">${course.course_description}</textarea></td>
                </tr>
                <tr>
                    <td>課程日期：</td>
                    <td><input type="text" disabled value="${course.course_date}"></td>
                </tr>
                <tr>
                    <td>教練編號：</td>
                    <td><input type="text" disabled value="${course.coach_id}"></td>
                </tr>
                <tr>
                    <td>教練名字：</td>
                    <td><input type="text" disabled value="${course.coach_name}"></td>
                </tr>
                <tr>
                    <td>課程時長：</td>
                    <td><input type="text" disabled value="${course.course_time}"></td>
                </tr>
                <tr>
                    <td>課程人數：</td>
                    <td><input type="text" disabled value="${course.max_capacity}"></td>
                </tr>
            </table>
        </form>
        <br>
        <a href="http://localhost:8080/HealthManagement/jsp/course/index.jsp" class="back-button">返回課程管理</a>
    </div>
        <div id="footer">
        <p>© 2025 健康管理系統. All Rights Reserved.</p>
    </div>
</body>
    <script>
        $(document).ready(function() {
            $("form").submit(function(event) {
                event.preventDefault();  // 阻止表單默認提交

                var course_id = $("input[name='course_id']").val(); // 取得用戶輸入的課程編號

                $.ajax({
                    url: "${pageContext.request.contextPath}/CourseDAO2",  // 設置提交的 URL
                    method: "POST",
                    data: {
                        action: "find",
                        course_id: course_id  // 提交課程編號
                    },
                    success: function(response) {
                        // 如果查詢成功，將返回的課程信息顯示在表格中
                        $("body").html(response);  // 更新頁面顯示查詢結果
                    },
                    error: function() {
                        alert("查詢過程中出錯，請稍後再試！");
                    }
                });
            });
        });
    </script>
</html>
