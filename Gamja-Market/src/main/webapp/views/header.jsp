<%@page import="client.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../resources/css/grid.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="https://www.gstatic.com/firebasejs/7.2/firebase.js"></script>
	<script src="../chat/firebase.js"></script>
</head>
<body>
	<header>
		<c:set var="name" value="" />
		<c:if test="${not empty sessionScope.log}">
			<c:set var="name" value="${sessionScope.log.clientNickname}" />
			<input type="hidden" id="chatUser"
				value="${sessionScope.log.clientNickname}" />
		</c:if>
		<div class="logo">
			<img id="logo" src="resources/img/logo.png" width="50px"
				height="50px">
			<h1>
				<a id="logo-title" href="/">감자마켓</a>
			</h1>
		</div>
		<nav>
		<ul id="nav-list">
			<li><a href="saleboard">중고거래</a></li>
			<li><a href="board">동네생활</a></li>
			<c:choose>
				<c:when
					test="${!empty sessionScope.log or !empty sessionScope.admin }">
					<li><a href="interests">관심목록</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="login">관심목록</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="/">홈으로</a></li>
		</ul>
	</nav>
		<c:choose>
			<c:when test="${sessionScope.admin != null}">
				<ul class="mylist">
					<li><a href="admin">관리자 모드</a></li>
					<li><a href="logout">로그아웃</a></li>
				</ul>
			</c:when>
			<c:when test="${sessionScope.log == null}">
				<ul class="mylist">
					<li><a href="login">로그인</a></li>
					<li><a href="join">회원가입</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<ul class="mylist">
					<li><a href="clientPageList">마이페이지</a></li>
					<li><a id="chatList" href="chatList" >채팅목록</a></li>
					<li><a href="logout">로그아웃</a></li>
				</ul>
			</c:otherwise>
		</c:choose>
	</header>
</body>
	<script src="../chat/chatAlert.js"></script>
</html>