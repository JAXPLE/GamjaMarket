<%@page import="client.Client"%>
<%@page import="Tool.Tool"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/css/chatList.css">
<title>채팅목록</title>
</head>
<body>
    <c:import url="header" />
    <section>
        <div id="container">
        <h2>나의 채팅목록</h2>
            <input type="hidden" value="${sessionScope.log.clientNickname}" id="currentUser">
            <div id="list-container"></div>
            <div id="button-box"></div>
        </div>
    </section>
    <script src="../chat/chatList.js"></script>
    <c:import url="footer" />
</body>
</html>
