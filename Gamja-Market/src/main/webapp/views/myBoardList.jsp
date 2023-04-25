<%@page import="Tool.Tool"%>
<%@page import="category.controller.CategoryDao"%>
<%@page import="client.controller.ClientDao"%>
<%@page import="client.Client"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="board.Board"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.controller.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../resources/js/alert.js"></script>
<title>내 게시글 목록</title>
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
			<c:if test="${not empty param.delFinish}">
				<script>
					alertDelete();
				</script>
			</c:if>

			<h2>내가 쓴 게시글</h2>

			<c:set var="client" value="${sessionScope.log}" />
			<c:if test="${empty client}">
				<c:redirect url="login" />
			</c:if>

			<c:set var="boardDao" value="${BoardDao.getInstance()}" />
			<c:set var="list"
				value="${boardDao.getBoardByBWriter(client.clientContact)}" />

			<form>
				<table>
					<thead>
						<tr>
							<th>카테고리</th>
							<th>작성자</th>
							<th>작성일자</th>
							<th>제목</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${list}">
							<c:set var="bcateCode" value="${board.bcateCode}" />
							<c:set var="date"
								value="${empty board.bModifyDate ? board.bPublishDate : board.bModifyDate}" />
							<tr>
								<td><a href="boardContent?bCode=${board.bCode}">${Tool.getbCateName(bcateCode)}</a></td>
								<td><a href="boardContent?bCode=${board.bCode}">${Tool.getClientNickname(board.bWriter)}</a></td>
								<td><a href="boardContent?bCode=${board.bCode}">${Tool.getTime(date)}</a></td>
								<td><a href="boardContent?bCode=${board.bCode}">${board.bTitle}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</section>
	</div>
	<c:import url="footer" />
</body>
</html>
