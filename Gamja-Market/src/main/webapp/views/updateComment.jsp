<%@page import="Tool.Tool"%>
<%@page import="comment.Comment"%>
<%@page import="comment.controller.CommentDao"%>
<%@page import="boardCateogry.BoardCategory"%>
<%@page import="category.controller.CategoryDao"%>
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
<title>댓글 수정</title>
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
			<c:set var="commentDao" value="${CommentDao.getInstance()}" />
			<c:set var="comCode" value="${param.comCode}" />
			<c:set var="comment" value="${commentDao.getCommentByComCode(comCode)}" />
			<%-- <c:out value="${comment}" /> --%>
			<c:set var="comCode" value="${comment.comCode}" />
			<c:set var="comContent" value="${comment.comContent}" />
			<c:set var="comDate" value="${comment.comDate}" />

			<section>
				<h2>댓글 수정</h2>
				<form method="POST" action="../service">
					<input type="hidden" name="command" value="updateComment" /> <input
						type="hidden" id="bCode" name="bCode" value="${comment.bCode}" />
					<input type="hidden" id="comCode" name="comCode" value="${comCode}" />
					<%-- <c:out value="${comCode}" /> --%>
					<table border="1">
						<tr>
							<td>내용</td>
							<td><input type="text" id="comContent" name="comContent"
								value="${comContent}" /> <%-- <c:out value="${comContent}" /> --%></td>
						</tr>
						<tr>
							<td>수정일자</td>
							<td><input type="text" id="comDate" name="comDate"
								value="${comDate}" readonly /> <%-- <c:out value="${comDate}" /> --%></td>
						</tr>
					</table>
					<input type="button" value="수정하기"
						onclick="checkUpdateCommentValues(this.form)" />
				</form>
			</section>



			<script src="resources/js/commentValidation.js" charset="UTF-8"></script>
		</section>
		<c:import url="footer" />
	</div>
</body>
</html>