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
			<h1>판매 페이지</h1>
			<c:set var="saleboardDao" value="${SaleboardDao.getInstance()}" />
			<c:set var="categoryDao" value="${CategoryDao.getInstance()}" />
			<c:set var="saleList" value="${saleboardDao.getAllLisBySaleboard()}" />
			<c:set var="cateList" value="${categoryDao.getCategoryAll()}" />
			<c:set var="cateSearch" value="${fn:split('', ',')}" />
			<c:set var="size" value="${cateList.size()+1}" />
			
			<div class="categoryBox">
				<div class = "addCategory">
					<form method="post" action="../service">
						<legend>추가할 카테고리 이름</legend>
						<input type="hidden" name="command" value="addCate"> 
						<input type="hidden" id="cateCode" name="cateCode" value="${Tool.getSize(size)}"> 
						<input type="text" id="cateName" name="cateName" placeholder="카테고리 이름을 입력하세요">
						<input type="submit" value="추가하기">
					</form>
				</div>
				<div class="deleteCategory">
					<form method="post" action="../service">
						<legend>카테고리 삭제</legend>
						<input type="hidden" name="command" value="deleteCate"> <select
							id="cateCode" name="cateCode">
							<c:forEach var="category" items="${cateList}">
								<option value="${category.cateCode}">${category.cateName}</option>
							</c:forEach>
						</select> <input type="submit" value="삭제하기">
					</form>
				</div>
			</div>

			<div class="board">
				<table>
					<thead>
						<tr>
							<th><h2>카테고리</h2></th>
							<th><h2>이미지</h2></th>
							<th><h2>제목</h2></th>
							<th><h2>내용</h2></th>
							<th><h2>가격</h2></th>
							<th><h2>판매자</h2></th>
							<th><h2>구매자</h2></th>
							<th><h2>수정일자</h2></th>
							<th><h2>수정횟수</h2></th>
							<th><h2>최초등록일</h2></th>
							<th><h2>이동하기</h2></th>
							<th><h2>삭제하기</h2></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="saleboard" items="${saleList}">
							<tr>
								<th>${Tool.getbCateName(saleboard.cateCode)}</th>
								<th><img src="${saleboard.sbImg}"></th>
								<th>${saleboard.sbTitle}</th>
								<th>${saleboard.sbContent}</th>
								<th>${saleboard.sbPrice}</th>
								<th>${Tool.getClientNickname(saleboard.sbSellClient)}</th>
								<th>${Tool.getClientNickname(saleboard.sbBuyClient)}</th>
								<th>${Tool.getTime(saleboard.sbModifyDate)}</th>
								<th>${saleboard.sbModiCount}</th>
								<th>${Tool.getTime(saleboard.sbPublishDate)}</th>
								<th>
									<button type="button" onclick="location.href = 'viewSale?code=${saleboard.sbCode}'">이동</button>
								</th>
								<th><button class="button"
										onclick="location.href='service?command=deleteSell&code=${saleboard.sbCode}'">삭제</button></th>
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