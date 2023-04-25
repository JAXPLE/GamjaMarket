<%@page import="boardCateogry.controller.BoardCategoryDao"%>
<%@page import="java.util.ArrayList"%>
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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/adminSellListForm.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<c:import url="/header" />
		<section>
			<h1>동네 생활 게시판</h1>
				<c:set var="boardDao" 		  value="${BoardDao.getInstance()}" />
				<c:set var="categoryDao" 	  value="${BoardCategoryDao.getInstance()}" />
				<c:set var="boardList" 		  value="${boardDao.getBoardAll()}" />
				<c:set var="cateList" 		  value="${categoryDao.getBoardCategoryAll()}" />
				<c:set var="size" 			  value="${cateList.size()+1}" />

			<div class="categoryBox">
				<div class ="addCategory">
					<form method="post" action="../service">
						<legend>추가할 카테고리 이름</legend>
						<input type="hidden" name="command" value="addBcate"> <input
							type="hidden" id="bcateCode" name="bcateCode"
							value="${Tool.getSize(size) }"> <input type="text"
							id="bcateName" name="bcateName" placeholder="카테고리 이름을 입력하세요">
						<input type="submit" value="추가하기">
					</form>
				</div>

				<div class ="deleteCategory>">
					<form method="post" action="../service">
						<legend>카테고리 삭제</legend>
						<input type="hidden" name="command" value="deleteBcate"> <select
							id="bcateCode" name="bcateCode">
							<c:forEach var="boardCategory" items="${cateList}">
								<option value="${boardCategory.bcateCode}">${boardCategory.bcateName}</option>
							</c:forEach>
						</select> <input type="submit" value="삭제하기">
					</form>
				</div>
			</div>
			<div class="board">
				<table>
					<thead>
						<tr class="menu">
							<th><h2>보드코드</h2></th>
							<th><h2>카테고리코드</h2></th>
							<th><h2>제목</h2></th>
							<th><h2>내용</h2></th>
							<th><h2>작성자</h2></th>
							<th><h2>최초작성일</h2></th>
							<th><h2>수정일자</h2></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${boardList}">
							<tr>
								<th>${board.bCode}</th>
								<th>${Tool.getbCateName(board.bcateCode)}</th>
								<th>${board.bTitle}</th>
								<th>${board.bContent}</th>
								<th>${Tool.getClientNickname(board.bWriter)}</th>
								<th>${board.bPublishDate}</th>
								<th>${board.bModifyDate}</th>
								<th>
									<button type="button" onclick="location.href = 'boardContent?bCode=${board.bCode}'">이동하기</button>
								<th>
									<button class="button" onclick="location.href='service?command=deleteBoard&bCode=${board.bCode}'">삭제하기</button></th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</section>
		<c:import url="/footer" />
	</div>
</body>
</html>