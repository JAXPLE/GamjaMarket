<%@page import="comment.controller.CommentDao"%>
<%@page import="comment.Comment"%>
<%@page import="java.util.Arrays"%>
<%@page import="boardCateogry.BoardCategory"%>
<%@page import="board.Board"%>
<%@page import="boardCateogry.BoardCategoryRequestDto"%>
<%@page import="board.controller.BoardDao"%>
<%@page import="category.Category"%>
<%@page import="category.controller.CategoryDao"%>
<%@page import="Tool.Tool"%>
<%@page import="java.util.List"%>
<%@page import="saleboard.Saleboard"%>
<%@page import="saleboard.controller.SaleboardDao"%>
<%@page import="saleboard.SaleboardRequestDto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/adminSellListForm.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<c:import url="/header"/>
		<section>
			<h1>댓글 게시판</h1>
			
			<c:set var="commentDao" value="<%=CommentDao.getInstance()%>" />
			<c:set var="commentList" value="${commentDao.getCommentByAll()}" />
			<div class="board">
			<table>
				<thead>
					<tr>
						<th><h2>댓글코드</h2></th>
						<th><h2>작성되어 있는 게시판</h2></th>
						<th><h2>댓글내용</h2></th>
						<th><h2>댓글작성자</h2></th>
						<th><h2>작성일</h2></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${commentList}" var="comment">
						<tr>
							<th>${comment.getComCode()}</th>
							<th>${comment.getComContent()}</th>
							<th>${Tool.getClientNickname(comment.getClientContact())}</th>
							<th>${comment.getComDate()}</th>
							<th>
								<button type="button" onclick="location.href='boardContent?bCode=${comment.getbCode()}'">이동하기</button>
							</th>
							<th>
								<button class="button"
									onclick="location.href='service?command=deleteComment&comCode=${comment.getComCode()}'">
									삭제하기
								</button>
							</th>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</section>
		<c:import url="/footer"/>
	</div>
</body>
</html>