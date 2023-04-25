<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty requestScope.welcome and requestScope.welcome}">
	<input type="hidden" id="welcome" value="${requestScope.welcome }">
	</c:if>
	<c:if test="${not empty requestScope.logout and requestScope.logout}">
		<input type="hidden" id="logout" value="${requestScope.logout }">
	</c:if>
	<c:if test="${not empty requestScope.login and requestScope.login}">
	<input type="hidden" id="login" value="${requestScope.login }">
	</c:if>
	<c:if test="${not empty requestScope.modify and requestScope.modify}">
		<input type="hidden" id="modify" value="${requestScope.modify }">
	</c:if>
	<c:if test="${not empty requestScope.loginFail and requestScope.loginFail}">
		<input type="hidden" id="loginFail" value="${requestScope.loginFail }">
	</c:if>
	<c:if test="${not empty requestScope.leave and requestScope.leave}">
		<input type="hidden" id="leave" value="${requestScope.leave }">
	</c:if>
</body>
<script src="../resources/hiddenWork/alert.js"></script>
</html>