<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>享健你，遇見更好的自己．</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/gymstyle.css">
</head>
<style>
#list {
	color: white;
	padding: 0; /* 去除內邊距 */
	border: none;
	cursor: pointer;
	width: 150px; /* 設定固定寬度 */
	height: 50px; /* 設定固定高度 */
	text-align: center; /* 使文字居中 */
	line-height: 50px; /* 使文字垂直居中 */
	display: inline-block; /* 讓它像個按鈕一樣顯示 */
}

#list:hover {
	background-color: #c0c0c0;
}
/* 用於設定 input[type="submit"] 和 #list 類似的樣式 */
input[type="submit"] {
	color: #777; /* 文字顏色，與 #list 中的文字顏色一致 */
	padding: 0; /* 去除內邊距 */
	border: none; /* 去除邊框 */
	cursor: pointer; /* 顯示為可點擊 */
	width: 150px; /* 設定固定寬度 */
	height: 50px; /* 設定固定高度 */
	text-align: center; /* 使文字居中 */
	line-height: 50px; /* 使文字垂直居中 */
	display: inline-block; /* 顯示為塊級元素 */
	background-color: transparent; /* 背景透明 */
}

input[type="submit"]:hover {
	background-color: #c0c0c0; /* 鼠標懸停時的背景顏色 */
}
</style>
<body>
	<div id="header">
		<h1>享健你，遇見更好的自己．</h1>
		<h2>你，今天健了嗎？</h2>
	</div>

	<!-- ✅ 導覽列 -->
	<div id="navigation">
		<ul>
			<li><a
				href="http://localhost:8080/HealthManagement/jsp/course/HealthManagement.jsp">首頁</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/jsp/membercenter.jsp"
				>會員管理</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/product.html">商城購物</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/jsp/fitness/index.jsp">健身成效</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/jsp/course/index.jsp" class="active">課程管理</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/api/Social/post">社群論壇</a></li>
			<!-- ✅ 修正網址 -->
		</ul>
	</div>
	<div id="content" align="center">
		<div>
			<ul>
				<li id="list"><a href="schedule.jsp">查詢單筆課程</a></li>
				<br>
				<li id="list">
					<form method="post"
						action="${pageContext.request.contextPath}/CourseDAO2">
						<input type="hidden" name="action" value="findAll"> <input
							type="submit" value="查詢所有課程">
					</form>
				</li>
				<br>
				<li id="list"><a href="insert.jsp">新增課程資訊</a></li>
			</ul>
		</div>
	</div>

	<div id="footer">
		<p>© 2025 健康管理系統. All Rights Reserved.</p>
	</div>
</body>
</html>