<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>享健你，遇見更好的自己．</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/gymstyle.css">
    <!-- 引入 jQuery 和 jQuery UI 的 CSS 和 JS -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
</head>
<style>
input[type="date"], textarea {
    width: 50%;
    padding: 8px;
    font-size: 1em;
    border: 1px solid #ccc;
}
</style>
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
        <h2>新增課程資訊</h2>
        <form method="post" action="${pageContext.request.contextPath}/CourseDAO2">
            <input type="hidden" name="action" value="insert">
        <!-- 顯示新增成功訊息 -->
        <c:if test="${not empty successMessage}">
            <br><div style="color: #808080;">${successMessage}</div>
        </c:if>
            <table border="1">
                <tr>
                    <td>課程編號：</td>
                    <td><input type="text" name="course_id" value="${course.course_id}" required></td>
                </tr>
                <tr>
                    <td>課程名稱：</td>
                    <td><input type="text" name="course_name" value="${course.course_name}" required></td>
                </tr>
                <tr>
                    <td>課程描述：</td>
                    <td><textarea rows="5" name="course_description" style="overflow: hidden; resize: none;" required>${course.course_description}</textarea></td>
                </tr>
                <tr>
                    <td>課程日期：</td>
                    <td><input type="date" name="course_date" value="${course.course_date}" required></td>
                </tr>
                <tr>
                    <td>教練編號：</td>
                    <td><input type="text" name="coach_id" value="${course.coach_id}" required></td>
                </tr>
                <tr>
                    <td>教練名字：</td>
                    <td><input type="text" name="coach_name" value="${course.coach_name}" required></td>
                </tr>
                <tr>
                    <td>課程時長：</td>
                    <td><input type="text" name="course_time" value="${course.course_time}" required></td>
                </tr>
                <tr>
                    <td>課程人數：</td>
                    <td><input type="text" name="max_capacity" value="${course.max_capacity}" required></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="確定" />
        </form>
        <br>
        <a href="http://localhost:8080/HealthManagement/jsp/course/index.jsp" class="back-button">返回課程管理</a>
    </div>
    <div id="footer">
        <p>© 2025 健康管理系統. All Rights Reserved.</p>
    </div>
</body>
<script>
    $(document).ready(function(){
        // 將 'course_date' 的 input 元素初始化為日期選擇器
        $("input[name='course_date']").datepicker({
            dateFormat: "yy-mm-dd",  // 設定日期格式
            changeMonth: true,  // 可以選擇月份
            changeYear: true,   // 可以選擇年份
            showButtonPanel: true  // 顯示按鈕面板
        });
    });
</script>
</html>
