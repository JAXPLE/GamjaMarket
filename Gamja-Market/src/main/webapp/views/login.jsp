<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="../resources/css/form.css">
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section id="login">
			<h2>로그인</h2><br>
			<form method="post" action="../service" id="login-form">
				<input type="hidden" name="command" value="login"> <input
					type="text" name="contact" id="contact" required placeholder="contact" required><br>
				<input type="password" name="password" id="password" required
					placeholder="password" required><br> 
					<input id="loginbutton" type="button" value="로그인" onclick="loginCheck(form)" >
			</form>
		</section>
		<c:import url="footer" />
	</div>
</body>
<script src="../resources/js/loginValidation.js"></script>
</html> 