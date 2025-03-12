<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>享健你，遇見更好的自己．</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/gymstyle.css">
<style>
</style>
</head>
<body>
	<div id="header">
		<h1>享健你，遇見更好的自己．</h1>
		<h2>你，今天健了嗎？</h2>
	</div>

	<!-- ✅ 導覽列 -->
	<div id="navigation">
		<ul>
			<li><a href="http://localhost:8080/HealthManagement/jsp/course/HealthManagement.jsp">首頁</a></li>
			<li><a href="http://localhost:8080/HealthManagement/jsp/membercenter.jsp">會員管理</a></li>
			<li><a href="http://localhost:8080/HealthManagement/product.html">商城購物</a></li>
			<li><a href="http://localhost:8080/HealthManagement/jsp/fitness/index.jsp"  class="active">健身成效</a></li>
			<li><a href="http://localhost:8080/HealthManagement/jsp/course/index.jsp">課程管理</a></li>
			<li><a href="http://localhost:8080/HealthManagement/api/Social/post">社群論壇</a></li>
			<!-- ✅ 修正網址 -->
		</ul>
	</div>
	<br>
	<div align="center" id="content">
		<h1>更新運動紀錄</h1>

		<div class="form-container">
			<form action="../../api/fitness/progress" method="post">
				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="recordId" value="${record.recordId}"> <input
					type="hidden" name="userId" value="${record.userId}">

				<table border="1">
					<tr>
						<td>運 動 類 型：</td>
						<td><select name="exerciseType" required>
								<option value="瑜伽" selected>瑜伽 (Yoga)</option>
								<option value="重訓">重訓 (Weight Training)</option>
								<option value="有氧">有氧 (Cardio)</option>
						</select></td>
					</tr>
					<tr>
						<td>運動時長 (分鐘):</td>
						<td><input type="number" id="exerciseDuration"
							name="exerciseDuration" value="${record.exerciseDuration}"
							required min="1"></td>
					</tr>
					<tr>
						<td>運 動 日 期:</td>
						<td><input type="date" id="exerciseDate" name="exerciseDate"
							value="${record.exerciseDate}" required></td>
					</tr>
				</table>
				<input type="submit" value="確認">
			</form>


			<!-- 返回到紀錄頁面 -->
			<a class="back-button"
				href="../../api/fitness/progress?userId=${record.userId}">返回</a>
		</div>
	</div>
	<div id="footer">
		<p>&copy; 2025 享健你. All Rights Reserved.</p>
</body>
</html>
