<%@page import="Tool.Tool"%>
<%@page import="board.Board"%>
<%@page import="board.controller.BoardDao"%>
<%@page import="client.Client"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="client.controller.ClientDao"%>
<%@page import="category.controller.CategoryDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="comment.controller.CommentDao"%>
<%@page import="comment.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 게시글 목록</title>
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
			<h2>내가 쓴 댓글</h2>
			<c:if test="${empty sessionScope.log}">
				<c:redirect url="login" />
			</c:if>

			<c:if test="${not empty sessionScope.log}">
				<c:set var="commentDao" value="<%=CommentDao.getInstance()%>" />
				<c:set var="boardDao" value="<%=BoardDao.getInstance()%>" />

				<c:set var="cmtList"
					value="${commentDao.getCommentByClientContact(sessionScope.log.clientContact)}" />

				<form>
					<table>
						<thead>
							<tr>
								<th>카테고리</th>
								<th>제목</th>
								<th>작성자</th>
								<th>내용</th>
								<th>댓글단날짜</th>
								<th>삭제버튼</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="cmt" items="${cmtList}">
								<c:set var="board"
									value="${boardDao.getBoardByBcode(cmt.bCode)}" />
								<tr>
									<td><a href="boardContent?bCode=${cmt.bCode}">
											${Tool.getbCateName(board.bcateCode)} </a></td>
									<td><a href="boardContent?bCode=${cmt.bCode}">
											${board.bTitle} </a></td>
									<td><a href="boardContent?bCode=${cmt.bCode}">
											${Tool.getClientNickname(board.bWriter)} </a></td>
									<td><a href="boardContent?bCode=${cmt.bCode}">
											${cmt.comContent} </a></td>
									<td><a href="boardContent?bCode=${cmt.bCode}">
											${Tool.getTime(cmt.comDate)} </a></td>
									<td><input type="button" value="삭제하기"
										onclick="location.href='service?comCode=${cmt.comCode}&command=deleteComment'" />
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</c:if>
			</section>
				<c:import url="footer" />
	</div>
</body>
</html>