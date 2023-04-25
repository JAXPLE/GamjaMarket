<%@page import="client.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page import="client.controller.ClientDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="../resources/css/form.css">
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section id="join-session">
			<c:set var="clientDao" value="${ ClientDao.getInstance()}" />
			<c:set var="nicknameList" value="${clientDao.nicknameAll}" />
			<c:set var="contactList" value="${clientDao.contactAll}" />
			
			<h2>회원가입</h2><br>
			<form method="post" action="../service" id="join-form">
			  <input type="hidden" name="command" value="join" />
			  <input type="hidden" name="nicknameList" id="nicknameList" value="${nicknameList}" />
			  <input type="hidden" name="contactList" id="contactList" value="${contactList}" />
			  
			  <input type="text" name="contact" id="contact" required placeholder="연락처" value="${param.contact}" ${empty param.contact ? "autofocus" : ""} />
			  <p id="message"></p>
			  <input type="password" name="password" id="password" required placeholder="비밀번호" value="${param.password}" ${empty param.password ? "autofocus" : ""} /><br>
			  <input type="password" name="passwordcheck" id="passwordcheck" required placeholder="비밀번호 재입력" value="${param.passwordcheck}" ${empty param.passwordcheck ? "autofocus" : ""} />
			  <p id="passmessage"></p>
			  <input type="text" name="nickname" id="nickname" required placeholder="닉네임" value="${param.nickname}" ${empty param.nickname ? "autofocus" : ""} />
			  <p id="nickmessage"></p>
			 <div id="postcodeform">
			  <input type="text" name="postcode" id="postcode" placeholder="우편번호" required value="${param.postcode}" ${empty param.postcode ? "autofocus" : ""} />
			  <input id="search-postcode" type="button" onclick="execDaumPostcode()" value="우편번호 찾기" />
			 </div>
			 <div id="addressform">
			  <input type="text" name="address" id="address" placeholder="주소" required value="${param.address}" ${empty param.address ? "autofocus" : ""} />
			  <input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소" value="${param.detailAddress}" />
			  </div>
			  <input type="hidden" name="y" id="y" value="${param.y}"/>
			  <input type="hidden" name="x" id="x" value="${param.x}" /><br />
			  <input id="joinbutton" type="button" value="가입" onclick="checkValues(form)" />
			</form>
		</section>
		<c:import url="footer" />
	</div>
</body>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../resources/js/searchaddress.js"></script>
<script src="../resources/js/registValidation.js"></script>
</html>