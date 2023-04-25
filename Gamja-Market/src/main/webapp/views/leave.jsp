<%@page import="Tool.Tool"%>
<%@page import="client.controller.ClientDao"%>
<%@page import="client.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="../resources/css/form.css">
</head>
<body>
	<div class="container">
		<c:import url="header" />
			<section>
				<c:set var="client" value="${sessionScope.log}"/>
				<c:set var="contact" value="${client.clientContact}"/>
				<c:set var="password" value="${client.clientPassword}"/>
				
				
				<h2>회원탈퇴</h2>
				<form method="post" action="../service">
					<input type="hidden" name="command" value="leave">
					<input type="hidden" name="contact" id="contact" value="${contact}"><br> 
					<input type="password" id="password" name="password" required placeholder="비밀번호 재입력"><br> 
					<input id="leavebutton" type="button" value="탈퇴" onclick="checkPassword(form)"><br><br>
					<input id="leavebutton" type="button" value="뒤로가기" onclick="location.href='clientPage'">
					<input type="hidden" id="failleave" value="${param.failleave }">
				</form>
			</section>
		<c:import url="footer" />
	</div>
</body>
<script src="../resources/js/leaveValidation.js"></script>
<script src="../resources/hiddenWork/alert.js"></script>
</html>