<%@page import="Tool.Tool"%>
<%@page import="comment.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="comment.controller.CommentDao"%>
<%@page import="comment.CommentRequestDto"%>
<%@page import="client.Client"%>
<%@page import="board.Board"%>
<%@page import="board.controller.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/form.css">
<link rel="stylesheet" href="resources/css/boardContent.css">
<title>게시판</title>
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
			<c:set var="client" value="${sessionScope.log}" />
			<c:set var="bCode" value="${param.bCode}" scope="page"/>

			<c:set var="boardDao" value="${BoardDao.getInstance()}" />
			<c:set var="commentDao" value="${CommentDao.getInstance()}" />

			<c:set var="board" value="${boardDao.getBoardByBcode(bCode)}" />
			<c:set var="list" value="${commentDao.getComment(bCode)}" />

			<c:if test="${not empty client}">
				<c:set var="contact" value="${client.clientContact}" />
			</c:if>
			<c:if test="${empty client}">
				<c:set var="contact" value="" />
			</c:if>

			<c:set var="bWriter" value="${board.bWriter}" />
			
			<div class="board">
				<div class="titleLine">
					<div class="boardTitle">
						${board.bTitle}
					</div>
					<div class="boardWriter">
						${Tool.getClientNickname(bWriter)}
					</div>
				</div>
				<div class="boardContent">
					${board.bContent}
				</div>
				<div class="boardDate">
					${Tool.getTime(empty board.bModifyDate ? board.bPublishDate : board.bModifyDate)}
				</div>
			</div>
			<div class="comment">
				<div class="comHeader">
					<h3>Comment</h3>
				</div>
				
				<c:forEach var="cmt" items="${list}">
					<div class="line">
						<div class="comWriter">
							${Tool.getClientNickname(cmt.clientContact)}
						</div>
						<div class="comContent">
							${cmt.comContent}
						</div>
						<div class="comDate">
							${Tool.getTime(cmt.comDate)}
						</div>
						<div class="comButton">
							<c:if test="${(not empty client and cmt.clientContact eq client.clientContact) or (not empty client and client.clientAdmin eq 1) }">
								<input type="button" class="button cmtBtn" value ="수정하기" onclick="location.href = 'updateComment?comCode=${cmt.comCode}'">
								<input type="button" class="button cmtBtn" value ="삭제하기" onclick="location.href = 'service?comCode=${cmt.comCode}&page=boardContact&bCode=${board.bCode}&command=deleteComment'">
							</c:if>	
						</div>
					</div>
				</c:forEach>
			</div>
			<form method="POST" action="../service">
		        <input type="hidden" name="command" value="addComment">
		        <input type="hidden" name="bCode" value="${board.bCode}">
		        <input type="hidden" name="clientContact" value="${client.clientContact }">
		        <div class="commentForm">
					<c:if test="${not empty client}">
		        		<textarea id="comContent" name="comContent"></textarea>
		        		<input type="submit" value="작성하기">
	        		</c:if>
		        </div>
		    </form>
		    <div class="boardBtn">
				<c:choose>
					<c:when test="${(board.bWriter eq client.clientContact) or (not empty client and client.clientAdmin eq 1)}">
						<input type="button" class="button" value="수정하기" onclick="location.href = 'updateBoard?bCode=${board.bCode}'">
						<input type="button" class="button" value="삭제하기" onclick="location.href = 'service?bCode=${board.bCode}&command=deleteBoard'">
					</c:when>
				</c:choose>    
			    <input type="button" class="button" onclick="location.href='board'" value="목록으로">
		    </div>
		</section>
		<c:import url="footer" />
	</div>
</body>
<script src="../resources/js/commentValidation.js"></script>
</html>