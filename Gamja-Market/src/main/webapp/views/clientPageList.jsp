<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script src="../resources/hiddenWork/alert.js"></script>
<link rel="stylesheet" href="../resources/css/form.css">
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
		<c:if
			test="${param.sellDone}">
			<input type="hidden" id="sellDone" value="${param.sellDone }">
		</c:if>
		<h2>마이페이지</h2>
			<div class="list">
				<input type="submit" value="개인정보수정" onclick="location.href='clientPage'"> 
				<input type="button" value="판매글" onclick="location.href='mySellList'"> 
				<input type="button" value="구매글" onclick="location.href='myBuyList'">
				<input type="button" value="작성글" onclick="location.href='myBoardList'"> 
				<input type="button" value="작성댓글" onclick="location.href='myCommentList'">
			</div>
		</section>
		<c:import url="footer" />
	</div>
</body>
</html>