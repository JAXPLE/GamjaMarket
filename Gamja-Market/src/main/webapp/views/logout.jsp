<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>감자마켓</title>
</head>
<body>
<c:remove var="log" scope="session" />
<c:remove var="admin" scope="session" />
<input type="hidden" id="logout" value="true">
</body>
<script src="../resources/hiddenWork/alert.js"></script>
</html>