<%@page import="client.Client"%>
<%@page import="Tool.Tool"%>
<%@page import="client.controller.ClientDao"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="category.controller.CategoryDao"%>
<%@page import="saleboard.Saleboard"%>
<%@page import="java.util.ArrayList"%>
<%@page import="saleboard.controller.SaleboardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 판매 게시글</title>
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
			<h2>판매 목록</h2>
			<c:set var="client" value="${sessionScope.log}" />
			<c:if test="${client == null}">
				<c:redirect url="login" />
			</c:if>
			<c:if test="${client != null}">
				<c:set var="saleboardDao" value="${SaleboardDao.getInstance()}" />
				<c:set var="list"
					value="${saleboardDao.getSaleboardList(client.clientContact)}" />
				<table>
					<thead>
						<tr>
							<th>카테고리</th>
							<th>판매자</th>
							<th>작성일자</th>
							<th>수정일자</th>
							<th>사진</th>
							<th>제목</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="saleboard" items="${list}">
							<tr>
								<td>${Tool.getCateName(saleboard.cateCode)}</td>
								<td>${Tool.getClientNickname(saleboard.sbSellClient)}</td>
								<td>${Tool.getTime(saleboard.sbPublishDate)}</td>
								<td>${Tool.getTime(saleboard.sbModifyDate == null ? saleboard.sbPublishDate : saleboard.sbModifyDate)}</td>
								<td><a href="viewSale?code=${saleboard.sbCode}">${saleboard.sbImg}</a></td>
								<td><a href="viewSale?code=${saleboard.sbCode}">${saleboard.sbTitle}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</section>
		<c:import url="footer" />
	</div>
</body>
</html>