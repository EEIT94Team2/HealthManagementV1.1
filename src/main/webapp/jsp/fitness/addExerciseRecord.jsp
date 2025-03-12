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
/* 讓文字內容的td寬度不會過大 */
table td {
	text-align: justify;
	word-spacing: 18px;
	padding-left: 10px;
	padding-right: 10px;
	white-space: nowrap; /* 防止文字換行 */
}

/* 為包含文字的 td 設置固定的寬度 */
table td:first-child {
	width: 20%; /* 可以根據需要調整寬度 */
}

/* 讓表單元素 (例如 textarea) 寬度為100% */
table td input, table td select, table td textarea {
	width: 100% !important; /* 強制將寬度設置為100% */
	padding: 8px;
	font-size: 1em;
	box-sizing: border-box; /* 計算邊框和內邊距 */
}

/* 針對textarea設置特殊寬度 */
textarea {
	width: 100% !important; /* 強制將寬度設置為100% */
	padding: 8px;
	font-size: 1em;
	border: 1px solid #ccc;
	box-sizing: border-box; /* 計算邊框和內邊距 */
}

button[type="submit"] {
	background-color: #5d615d;
	color: white;
	padding: 5px 10px;
	border: none;
	cursor: pointer;
	border-radius: 2px;
	color: white;
	border: none;
	cursor: pointer;
	width: 200px;
	font-size: 1em;
}

button[type="submit"]:hover {
	background-color: #868B86;
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
			<li><a
				href="http://localhost:8080/HealthManagement/jsp/course/HealthManagement.jsp">首頁</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/jsp/membercenter.jsp">會員管理</a></li>
			<li><a href="http://localhost:8080/HealthManagement/product.html">商城購物</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/jsp/fitness/index.jsp"  class="active">健身成效</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/jsp/course/index.jsp">課程管理</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/api/Social/post">社群論壇</a></li>
			<!-- ✅ 修正網址 -->
		</ul>
	</div>
	<br>
<body>
	<div id="content" align="center">
		<h1>新增運動單筆紀錄</h1>

		<!-- 用戶輸入 User ID 並添加運動紀錄 -->
		<form action="../../api/fitness/progress" method="post">
			<input type="hidden" name="action" value="add">
			<table border="1" id="">
				<tr>
					<td>用 戶 I D：</td>
					<td><input type="text" name="userId" required
						placeholder="Enter User ID"></td>
				</tr>
				<tr>
					<td>運 動 類 型：</td>
					<td><select name="exerciseType" required>
							<option value="瑜伽" selected>瑜伽 (Yoga)</option>
							<option value="重訓">重訓 (Weight Training)</option>
							<option value="有氧">有氧 (Cardio)</option>
					</select></td>
				</tr>
				<tr>
					<td>運 動 時 長(分鐘)：</td>
					<td><input type="number" name="exerciseDuration" required
						placeholder="Enter Duration" value="20"></td>
				</tr>
				<tr>
					<td>運 動 日 期：</td>
					<td><input type="date" name="exerciseDate" required
						id="exerciseDate"></td>
				</tr>

			</table>

			<button type="submit">確認</button>

		</form>


		<script>
			// 獲取當天日期
			const today = new Date().toISOString().split('T')[0]; // 轉換為 'YYYY-MM-DD' 格式
			document.getElementById('exerciseDate').value = today; // 設置為 input 的預設值
		</script>

		<!-- 返回主畫面的按鈕 -->
		<a href="http://localhost:8080/HealthManagement/jsp/fitness/index.jsp"
			class="back-button">返回</a>
	</div>

	<div id="footer">
		<p>&copy; 2025 享健你. All Rights Reserved.</p>
	</div>
</body>
</html>
