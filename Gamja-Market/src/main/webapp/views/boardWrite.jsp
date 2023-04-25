<%@page import="boardCateogry.controller.BoardCategoryDao"%>
<%@page import="boardCateogry.BoardCategory"%>
<%@page import="category.controller.CategoryDao"%>
<%@page import="client.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.Board"%>
<%@page import="board.controller.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/css/form.css">
<link rel="stylesheet" href="../resources/css/viewSale.css">
<title>게시글 작성</title>
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<c:set var="client" value="${sessionScope.log}" />
		<c:if test="${empty client}">
			<c:redirect url="login" />
		</c:if>

		<c:set var="bcateCode" value="${param.bcateCode}" />
		<c:set var="bTitle" value="${param.bTitle}" />
		<c:set var="bContent" value="${param.bContent}" />

		<section>
			<div class="formBox">
				<form method="POST" action="../service">
					<input type="hidden" name="command" value="board">
					<div class="category">
						<select id="bcateCode" name="bcateCode">
							<c:set var="boardCategoryDao" value="${BoardCategoryDao.getInstance()}" />
							<c:set var="list" value="${boardCategoryDao.getBoardCategoryAll()}" />
							<c:forEach var="boardCategory" items="${list}">
								<option value="${boardCategory.bcateCode}"
									${not empty bcateCode && bcateCode == boardCategory.bcateCode ? 'selected' : ''}>${boardCategory.bcateName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="sbTitle">
						<input type="text" id="bTitle" name="bTitle"
							placeholder="제목을 입력하세요" value="${not empty bTitle ? bTitle : ''}" />
					</div>
					<div class="sbContent">
						<textarea id="bContent" name="bContent" rows="30" cols="100"
							wrap="soft">${not empty bContent ? bContent : ''}</textarea>
					</div>
					<div class="buttonBox">
						<input type="button" class="button" value="작성하기"
							onclick="checkBoardValues(form)">
					</div>
				</form>
			</div>
		</section>
		<script src="resources/js/boardValidation.js" charset="UTF-8"></script>
		<c:import url="footer" />
	</div>
</body>
</html>