<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>享健你，遇見更好的自己．</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/gymstyle.css">
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
		<h2>修改課程資訊</h2>
		<br>
		<table border="1">
			<tr>
				<td>課程編號：</td>
				<td><input type="text" name="course_id"
					value="${course.course_id}" /></td>
			</tr>
			<tr>
				<td>課程名稱：</td>
				<td><input type="text" name="course_name"
					value="${course.course_name}"></td>
			</tr>
			<tr>
				<td>課程描述：</td>
				<td><textarea rows="5" name="course_description"
						style="overflow: hidden;">${course.course_description}</textarea></td>
			</tr>
			<tr>
				<td>課程日期：</td>
				<td><input type="date" name="course_date"
					value="${course.course_date}" /></td>
			</tr>
			<tr>
				<td>教練編號：</td>
				<td><input type="text" name="coach_id"
					value="${course.coach_id}"></td>
			</tr>
			<tr>
				<td>教練名字：</td>
				<td><input type="text" name="coach_name"
					value="${course.coach_name}"></td>
			</tr>
			<tr>
				<td>課程時長：</td>
				<td><input type="text" name="course_time"
					value="${course.course_time}"></td>
			</tr>
			<tr>
				<td>課程人數：</td>
				<td><input type="text" name="max_capacity"
					value="${course.max_capacity}"></td>
			</tr>
		</table>
		<!-- 顯示新增成功訊息 -->
		<c:if test="${not empty successMessage}">
			<div style="color: red;">${successMessage}</div>
		</c:if>
		<br> <a href="index.jsp" class="back-button">返回</a>
	</div>
	<div id="footer">
		<p>© 2025 健康管理系統. All Rights Reserved.</p>
	</div>
</body>
</html>