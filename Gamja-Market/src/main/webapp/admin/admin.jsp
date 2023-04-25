<%@page import="client.controller.ClientDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../resources/hiddenWork/alert.js"></script>
<title>관리자 모드</title>
<link rel="stylesheet" href="../resources/css/adminIndexForm.css">
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
				<c:if test="${not empty param.delete}">
					<input type="hidden" id="delete" value="${param.delete }">
				</c:if>
	
				<c:if test="${not empty param.create}">
					<input type="hidden" id="create" value="${param.create }">
				</c:if>
				
			<div class="adminBox">
				<div class="deleteZone">
					<div class = "deleteUserSection">
						<!-- <form method="post" action="../service">
							<legend>회원 삭제</legend>
							<label for="contact">삭제할 회원 전화번호:</label> <input type="hidden" name="command" value="leave">
							<input type="text" id="contact" name="contact">
							<input type="submit" value="제출">
						</form> -->
						<h1>관리자페이지</h1>
					</div>
				</div>
				<div class="btnZone">
					<div class = "interactionBtn">
						<form method="post" action="../service">
							<input type="button" value="전체 판매글" onclick="location.href='adminSellList'">
							<input type="button" value="전체 생활글" onclick="location.href='adminBoardList'">
							<input type="button" value="전체 댓글" onclick="location.href='adminCommentList'">
						</form>
					</div>
				</div>
				
			</div>
			
<!--  		참고용입니당 

			<div>
				<form method="post" action="../service">
					<legend>게시글 삭제</legend>
					<label for="bCode">삭제할 게시글 번호:</label>
					<input type="hidden" name="command" value="deleteBoard">
					<input type="text" id="bCode" name="bCode">
					<input type="submit" value="제출">
				</form>
			</div>
			
			<div>
				<form method="post" action="../service">
					<legend>보드 카테고리 추가</legend>
					<label for="bcateCode">추가할 보드 카테고리 코드:</label>
					<input type="hidden" name="command" value="addBcate">
					 <input type="text" id="bcateCode" name="bcateCode">
					 <label for="bcateName">추가할 보드 카테고리 이름:</label>
					 <input type="text" id="bcateName" name="bcateName">
					 <input type="submit" value="제출">
				</form>
			</div>

			<div>
				<form method="post" action="../service">
					<legend>보드 카테고리 삭제</legend>
					<label for="bcateCode">삭제할 보드 카테고리 코드:</label> <input type="hidden"
						name="command" value="deleteBcate"> <input type="text"
						id="bcateCode" name="bcateCode"> <input type="submit"
						value="제출">
				</form>
			</div>
			<div>
				<form method="post" action="../service">
					<legend>판매게시글 삭제</legend>
					<label for="code">삭제할 판매게시글 번호:</label>
					<input type="hidden" name="command" value="deleteSell">
					<input type="text" id="code" name="code"> <input type="submit" value="제출">
				</form>
			</div>

			<div>
				<form method="post" action="../service">
					<legend>카테고리 추가</legend>
					<label for="cateCode">추가할 카테고리 코드:</label> <input type="hidden" name="command" value="addCate">
						<input type="text" id="cateCode" name="cateCode">
						<label for="cateName">추가할 카테고리 이름:</label>
						<input type="text" id="cateName" name="cateName">
					<input type="submit" value="제출">
				</form>
			</div>

			<div>
				<form method="post" action="../service">
					<legend>카테고리 삭제</legend>
					<label for="cateCode">삭제할 카테고리 코드:</label>
					<input type="hidden"name="command" value="deleteCate">
					<input type="text" id="cateCode" name="cateCode">
					<input type="submit" value="제출">
				</form>
			</div>
 -->

		</section>
		<c:import url="footer" />
	</div>
</body>
</html>