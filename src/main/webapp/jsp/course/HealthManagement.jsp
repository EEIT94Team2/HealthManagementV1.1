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

<body>
	<div id="header">
		<h1>享健你，遇見更好的自己．</h1>
		<h2>你，今天健了嗎？</h2>
	</div>
	<div id="navigation">
		<ul>
			<li><a
				href="http://localhost:8080/HealthManagement/jsp/course/HealthManagement.jsp"
				class="active">首頁</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/jsp/membercenter.jsp">會員管理</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/product.html">商城購物</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/jsp/fitness/index.jsp">健身成效</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/jsp/course/index.jsp">課程管理</a></li>
			<li><a
				href="http://localhost:8080/HealthManagement/api/Social/post">社群論壇</a></li>
		</ul>
	</div>
	<div id="content">
		<h1>About Us</h1>
		<p>
			我們的宗旨是提供一個<strong>簡便、高效且創新</strong>的健身房管理平台，旨在幫助健身房經營者和會員更輕鬆地管理各項健身服務。我們的系統不僅讓會員能夠輕鬆預約課程、管理健身計劃，也幫助健身房管理者高效追蹤會員資料、課程安排及商品銷售。

			我們致力於讓每位使用者都能夠擁有個性化的健身體驗，並隨時隨地掌握自己的健康狀況。我們的核心目標是推動健康生活，幫助人們發現更好的自己，無論是在身體、心靈還是生活方式上，都能夠實現更全面的提升。
		</p>

		<h1>Features</h1>
		<p>
		<ul>
			<li style="margin-left: 40px;"><strong>專業教練：</strong>擁有經驗豐富、專業認證的健身教練，提供個性化指導。</li>
			<li style="margin-left: 40px;"><strong>先進設施：</strong>配備頂尖的健身設備，幫助你更高效地達成健身目標。</li>
			<li style="margin-left: 40px;"><strong>多樣化課程：</strong>提供各種健身課程，包括瑜伽、有氧舞蹈、力量訓練等，適合不同需求的會員。</li>
		</ul>

		<h1>Benefits</h1>
		<p>
		<ul>
			<li style="margin-left: 40px;"><strong>專屬健身計劃：</strong>根據你的健身目標，量身定制專屬訓練計劃。</li>
			<li style="margin-left: 40px;"><strong>靈活的會籍制度：</strong>我們提供多種會員方案，靈活選擇，讓你根據自己的需求選擇最適合的計劃。</li>
			<li style="margin-left: 40px;"><strong>社區支持：</strong>加入健身社群，與其他會員一同挑戰自己，分享健身心得。</li>
		</ul>
		
		<h1>Advantage</h1>
		<p>健身對身體和心理有許多好處，以下是幾個簡單的介紹：
		<hr>
		</p>

		<ul>
			<li style="margin-left: 40px;"><strong>增強體能：</strong>健身能增強心肺功能和肌肉力量，讓你更有活力，完成日常活動更輕鬆。
			<li style="margin-left: 40px;"><strong>改善心臟健康：</strong>定期運動能降低心臟病風險，促進血液循環，保持血壓穩定。
			<li style="margin-left: 40px;"><strong>減少壓力：</strong>運動能釋放“快樂荷爾蒙”，幫助減輕壓力、焦慮和改善心情。
			<li style="margin-left: 40px;"><strong>維持健康體重：</strong>健身有助於燃燒卡路里，幫助保持或達到理想體重。
			<li style="margin-left: 40px;"><strong>改善睡眠質量：</strong>運動能促進放鬆，提高睡眠質量，讓你感覺精力充沛。
		</ul>
		
	</div>

	<div id="footer">
		<p>© 2025 健康管理系統. All Rights Reserved.</p>
	</div>
</body>
<script>
    // 選擇所有的 <a> 元素
    const links = document.querySelectorAll("#navigation a");

    // 為每個 <a> 元素添加點擊事件
    links.forEach(link => {
        link.addEventListener("click", function(event) {
            // 移除所有其他 <a> 元素的 'active' 類別
            links.forEach(link => link.classList.remove("active"));

            // 為當前點擊的 <a> 元素添加 'active' 類別
            this.classList.add("active");
        });
    });
</script>
</html>