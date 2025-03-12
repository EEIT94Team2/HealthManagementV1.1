<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>享健你，遇見更好的自己．</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/gymstyle.css">
<style>
#content ul {
    list-style-type: none; /* 去除預設的列表樣式 */
    padding: 0;
    margin: 0;
}

#content li {
    margin-bottom: 15px; /* 按鈕之間的垂直間距 */
}

#content form {
    display: block; /* 讓表單顯示為區塊元素 */
    width: 100%; /* 確保表單寬度填滿容器 */
}

#content button {
    display: inline-block; /* 讓按鈕顯示為行內塊級元素 */
    padding: 10px 20px; /* 調整按鈕內邊距 */
    background-color: #777; /* 按鈕背景顏色 */
    color: white; /* 文字顏色 */
    border: none; /* 去除按鈕邊框 */
    cursor: pointer; /* 讓按鈕顯示為可點擊 */
    text-align: center; /* 文字置中 */
    width: 100%; /* 按鈕填滿父容器的寬度 */
    max-width: 300px; /* 限制按鈕的最大寬度 */
}

#content button:hover {
    background-color: #333; /* 按鈕 hover 效果 */
}


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
	<div align="center">
		<h2>運動數據記錄</h2>
		<div id="content">
		<ul>
			<!-- 新增運動紀錄的按鈕 -->
			<li>
				<form action=../../jsp/fitness/addExerciseRecord.jsp method="get">
					<button type="submit">新增運動紀錄</button>
				</form>
			</li><hr>

			<!-- 查詢運動紀錄 -->
			<li>
				<form action=../../jsp/fitness/findUserRecords.jsp method="get">
					<button type="submit">查詢用戶紀錄</button>
				</form>
			</li><hr>

			<!-- 查詢所有用戶運動紀錄 -->
			<li>
				<form action=../../api/fitness/progress method="get">
					<button type="submit" name="action" value="all">所有用戶紀錄</button>
				</form>
			</li><hr>
		</ul>
	</div>
	</div>
	<div id="footer">
		<p>&copy;  2025 享健你. All Rights Reserved.</p>
	</div>
</body>
</html>
