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
</head>
<style>
.update {
    background-color:#c5c9a3;
    color: white; /* 文字顏色為白色 */
    padding: 12px 20px; /* 增加內邊距，使按鈕更大 */
    border-radius: 3px; /* 讓按鈕邊角變圓 */
    font-size: 1em; /* 字體大小 */
    cursor: pointer;
}

.update:hover {
    background-color: #e3ebae; /* 滑鼠懸停時的顏色變化 */
}

input[type="submit"] {
  border-radius: 3px; 
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
			<li><a href="http://localhost:8080/HealthManagement/jsp/course/HealthManagement.jsp">首頁</a></li>
			<li><a href="http://localhost:8080/HealthManagement/jsp/membercenter.jsp">會員管理</a></li>
			<li><a href="http://localhost:8080/HealthManagement/product.html">商城購物</a></li>
			<li><a href="http://localhost:8080/HealthManagement/jsp/fitness/index.jsp"  class="active">健身成效</a></li>
			<li><a href="http://localhost:8080/HealthManagement/jsp/course/index.jsp">課程管理</a></li>
			<li><a href="http://localhost:8080/HealthManagement/api/Social/post">社群論壇</a></li>
		</ul>
	</div>
	<div id="content" align="center">

		<h1>運動紀錄</h1>

		<!-- 顯示錯誤訊息 -->
		<c:if test="${not empty errorMessage}">
			<div class="error-message">
				<strong>Error:</strong> ${errorMessage}
			</div>
		</c:if>

		<!-- 顯示成功訊息 -->
		<c:if test="${not empty successMessage}">
			<div class="alert-success">
				<c:choose>
					<c:when test="${successMessage == 'Record Deleted Successfully'}">
						<strong>Success:</strong> Record Deleted Successfully
                </c:when>
					<c:otherwise>
                    ${successMessage}
                </c:otherwise>
				</c:choose>
			</div>
		</c:if>

		<!-- 顯示運動紀錄 -->
		<c:if test="${not empty records}">
			<table>
				<thead>
					<tr>
						<th>用戶ID</th>
						<th>用戶姓名</th>
						<th>運動類型</th>
						<th>運動時長（分鐘）</th>
						<th>卡路里消耗</th>
						<th>日期</th>
						<th>管理</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="record" items="${records}">
						<tr>
							<td>${record.userId}</td>
							<td>${record.user.name}</td>
							<td>${record.exerciseType}</td>
							<td>${record.exerciseDuration}</td>
							<td>${record.caloriesBurned}</td>
							<td>${record.exerciseDate}</td>
							<td>
								<!-- 更新按鈕，會跳轉到更新頁面 --> <a
								href="../../api/fitness/progress?action=update&recordId=${record.recordId}&userId=${record.userId}">
									<input type="button" value="更新" class="update">
							</a> <!-- 刪除表單 -->
								<form action="../../api/fitness/progress" method="post"
									style="display: inline;">
									<input type="hidden" name="action" value="delete"> <input
										type="hidden" name="recordId" value="${record.recordId}">
									<input type="hidden" name="userId" value="${record.userId}">
									<input type="submit" value="刪除" class="delete">
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- 顯示資料總數 -->
			<div class="record-count">
				<p>總共有: ${fn:length(records)} 筆資料</p>
			</div>
		</c:if>

		<c:if test="${empty records}">
			<p>No records found for the given User ID.</p>
			<p>該用戶沒有運動紀錄</p>
		</c:if>

		<!-- 返回主畫面的按鈕 -->
		<button class="back-button" style="cursor: pointer"
			onclick="window.location.href='../../jsp/fitness/index.jsp'">返回</button>
	</div>
	<div id="footer">
		<p>&copy; 2025 享健你. All Rights Reserved.</p>
	</div>
</body>
</html>
