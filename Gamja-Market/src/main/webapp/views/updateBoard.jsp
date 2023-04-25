<%@page import="boardCateogry.controller.BoardCategoryDao"%>
<%@page import="boardCateogry.BoardCategory"%>
<%@page import="client.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Timestamp"%>
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
<title>게시글 수정</title>
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
			<c:set var="client" value="${sessionScope.log}" />
			<c:set var="bCode" value="${param.bCode}" />
			<c:set var="updateBoardbCode" value="${bCode}" scope="session" />
			<c:set var="boardDao" value="${BoardDao.getInstance()}" />
			<c:set var="board" value="${boardDao.getBoardByBcode(bCode)}" />

			<c:set var="bTitle"
				value="${not empty param.bTitle ? param.bTitle : board.bTitle}" />
			<c:set var="bContent"
				value="${not empty param.bContent ? param.bContent : board.bContent}" />
			<c:set var="bcateCode"
				value="${not empty param.bcateCode ? param.bcateCode : board.bcateCode}" />

			<c:set var="date"
				value="${empty board.bModifyDate ? board.bPublishDate : board.bModifyDate}" />
			<c:set var="clientNickname" value="${client.getClientNickname()}" />
			<c:set var="bTitleValue" value="${not empty bTitle ? bTitle : ''}" />
			<c:set var="bContentValue" value="${not empty bContent ? bContent : ''}" />
			
			<div class="formBox">
				<form method="POST" action="../service">
					<input type="hidden" name="command" value="updateBoard">
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
							placeholder="제목을 입력하세요" value="${bTitleValue }" />
					</div>
					<div class="sbContent">
						<textarea id="bContent" name="bContent" rows="30" cols="100"
							wrap="soft">${bContentValue}</textarea>
					</div>
					<div class="buttonBox">
					<input type="button" value="수정하기"
						onclick="checkUpdateBoardValues(form)">
					</div>
				</form>
			
			</div>
		</section>
		<script src="resources/js/boardValidation.js" charset="UTF-8"></script>
		<c:import url="footer" />
	</div>
</body>
</html>