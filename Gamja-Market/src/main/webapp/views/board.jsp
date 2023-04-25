<%@page import="boardCateogry.controller.BoardCategoryDao"%>
<%@page import="boardCateogry.BoardCategory"%>
<%@page import="java.util.List"%>
<%@page import="category.Category"%>
<%@page import="category.controller.CategoryDao"%>
<%@page import="Tool.Tool"%>
<%@page import="client.controller.ClientDao"%>
<%@page import="board.controller.BoardDao"%>
<%@page import="board.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/css/board.css">
<title>게시판</title>
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
			<c:set var="categoryList"
				value="${BoardCategoryDao.getInstance().getBoardCategoryAll()}" />
			<c:set var="bcateCode" value="${param.bcateCode}" />
			<c:set var="pages" value="${not empty param.pages ? param.pages : 1}" />
			<div class="sidebar">
				<ul>
					<c:forEach var="boardCategory" items="${categoryList}">
						<li>
						<a href="board?bcateCode=${boardCategory.bcateCode}">${boardCategory.bcateName}</a>
						</li>
					</c:forEach>
				</ul>
			</div>

			<div class="searchBar">
				<form action=board>
					<input type="hidden" name="cateCode" value="${bcateCode}">
					<select name="search">
						<option value="b_title">제목</option>
						<option value="b_content">내용</option>
					</select> <input type="text" name="value"> <input type="submit"
						value="search">
				</form>
			</div>
			<c:set var="search" value="${param.search}" />
			<c:set var="value" value="${param.value}" />
			<c:choose>
				<c:when test="${empty bcateCode}">
					<c:choose>
						<c:when test="${not empty search and not empty value}">
							<c:set var="list"
								value="${BoardDao.getInstance().getBoardList(search, value)}" />
						</c:when>
						<c:otherwise>
							<c:set var="list"
								value="${BoardDao.getInstance().getBoardAll(pages)}" />
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${not empty search and not empty value}">
							<c:set var="list"
								value="${BoardDao.getInstance().getBoardListByCateCode(bcateCode, search, value)}" />
						</c:when>
						<c:otherwise>
							<c:set var="list"
								value="${BoardDao.getInstance().getBoardListByCateCode(bcateCode)}" />
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>

			<div class="board">
				<c:forEach var="board" items="${list}">
					<div class="line">
						<div class="cateName">${Tool.getbCateName(board.bcateCode)}
						</div>
						<div class="boardTitle">
							<a href="boardContent?bCode=${board.bCode}">${board.bTitle}</a>
						</div>
						<div class="boardDate">${Tool.getTime(empty board.bModifyDate ? board.bPublishDate : board.bModifyDate)}
						</div>
						<div class="boardWriter">
							${Tool.getClientNickname(board.bWriter)}</div>
					</div>
				</c:forEach>
			</div>

			<div class="pages">
				<ul>
					<c:set var="pageSize"
						value="${BoardDao.getInstance().getBoardSize() / 10 + 1}" />
					<c:forEach begin="0" end="${pageSize-1}" varStatus="i">
						<li><a href="board?pages=${i.count}">${i.count}</a></li>
					</c:forEach>
				</ul>
			</div>
			<form id="button-box">
				<input type="button" class="button"
					onclick="location.href = 'boardWrite'" value="작성하기">
			</form>
		</section>
		<c:import url="footer" />
	</div>
</body>
</html>