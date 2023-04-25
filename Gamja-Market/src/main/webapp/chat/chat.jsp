<%@page import="Tool.Tool"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/css/chat.css">

<title>채팅방</title>
</head>
<body>
	<section>
		<c:import url="header" />
		<div id="container">
			<input id="sbCode" type="hidden" value="${param.sbCode }" /> <input
				id="currentUser" type="hidden" value="${param.currentUser }" /> <input
				id="otherUser" type="hidden" value="${param.otherUser }" /> <input
				id="seller" type="hidden" value="${param.seller }" /> <input
				id="title" type="hidden" value="${param.title }" />
			<p id="title">${param.title }</p>
			<p id="otherUser">${param.otherUser }</p>
			<div id="chat-container"></div>
			<div id="button-box">
				<form id="chatForm"
					onsubmit="event.preventDefault(); writeChatData(content.value); content.value = '';">
					<input type="text" id="content" onkeydown="handleEnterKey(event)">
					<input type="submit" value="채팅보내기">
				</form>
			</div>
		</div>
	</section>
	<script src="../chat/chat.js"></script>
</body>
</html>
