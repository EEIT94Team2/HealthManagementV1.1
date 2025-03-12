<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />
<!-- 刪除文章按鈕 -->
<form method="post" action="${pageContext.request.contextPath}/api/Social/delete" onsubmit="return confirm('確定要刪除這篇文章嗎？');">
    <input type="hidden" name="articleId" value="${param.articleId}">
    <button type="submit">刪除</button>
</form>
