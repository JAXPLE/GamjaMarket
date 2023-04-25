<%@page import="Tool.Tool"%>
<%@page import="category.controller.CategoryDao"%>
<%@page import="category.Category"%>
<%@page import="client.Client"%>
<%@page import="client.controller.ClientDao"%>
<%@page import="saleboard.Saleboard"%>
<%@page import="java.util.ArrayList"%>
<%@page import="saleboard.controller.SaleboardDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/grid.css">
<link rel="stylesheet" href="resources/css/saleboard.css">
<title>Insert title here</title>

</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
			<c:set var="log" value="${sessionScope.log}" />
			<c:set var="categoryDao" value="${CategoryDao.getInstance()}" />
			<c:set var="categoryList" value="${categoryDao.getCategoryAll()}" />
			<c:set var="cateCode" value="${param.cateCode}" />

			<div class="sidebar">
				<ul>
					<c:forEach var="category" items="${categoryList}">
						<li><a href="saleboard?cateCode=${category.cateCode}">${category.cateName}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="searchBar">
				<form action="saleboard">
					<input type="hidden" name="cateCode" value="${cateCode}"> 
					<select	name="search">
						<option value="sb_title">제목</option>
						<option value="sb_content">내용</option>
					</select>
					<input type="text" name="value" placeholder="검색어를 입력하세요">
					<input type="submit" value="search">
				</form>
			</div>
			<div class="saleboard">
				<c:set var="search" value="${param.search}" />
				<c:set var="value" value="${param.value}" />
				<c:set var="saleboardDao" value="${SaleboardDao.getInstance()}" />
				<c:set var="list" value="${null}" />
				<c:choose>
					<c:when test="${empty cateCode or cateCode eq 'null'}">
						<c:choose>
							<c:when test="${not empty search and not empty value}">
								<c:set var="list"
									value="${saleboardDao.getSaleboardList(log, search, value)}" />
							</c:when>
							<c:otherwise>
								<c:set var="list" value="${empty log? saleboardDao.getSaleboardListAll(): saleboardDao.getSaleboardList(log)}" />
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${not empty search and not empty value}">
								<c:set var="list"
									value="${saleboardDao.getSaleboardListByCateCode(log, cateCode, search, value)}" />
							</c:when>
							<c:otherwise>
								<c:set var="list"
									value="${saleboardDao.getSaleboardListByCateCode(log, cateCode)}" />
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>

				<c:forEach var="board" items="${list}">
					<c:set var="sbSellClient" value="${board.sbSellClient}" />
					<c:set var="clientDao" value="${ClientDao.getInstance()}" />
					<c:set var="user"
						value="${clientDao.getClientByContact(sbSellClient)}" />
					<a href="viewSale?code=${board.sbCode}">
						<div class="cards">
							<div class="imgBox">
								<img src="${board.sbImg}" />
							</div>
							<div class="content">
								<p>${board.sbTitle}</p>
								<p>${board.sbPrice}원</p>
								<p>${user.clientAddress}</p>
								<p>관심 ${saleboardDao.countOfInterestsBySbCode(board.sbCode)}</p>
							</div>
						</div>
					</a>
				</c:forEach>

			</div>

			<c:if test="${not empty log}">
				<button class="button" onclick="location.href='saleForm'">+글쓰기</button>
			</c:if>
		</section>
		<c:import url="footer" />
	</div>
</body>
</html>