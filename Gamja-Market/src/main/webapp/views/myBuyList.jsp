<%@page import="Tool.Tool"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="client.controller.ClientDao"%>
<%@page import="saleboard.Saleboard"%>
<%@page import="java.util.ArrayList"%>
<%@page import="saleboard.controller.SaleboardDao"%>
<%@page import="client.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../resources/js/welcome.js"></script>
<script src="../resources/js/logout.js"></script>
<title>구매 목록</title>
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
			<h2>구매 목록</h2>
			<c:set var="client" value="${sessionScope.log}" />
			<c:if test="${client == null}">
				<c:redirect url="login" />
			</c:if>
			<c:if test="${client != null}">
				<c:set var="saleboardDao" value="${SaleboardDao.getInstance()}" />
				<c:set var="list"
					value="${saleboardDao.getSaleboardListForBuyer(client.clientContact)}" />
			</c:if>
			<c:if test="${not empty log}">
				<c:set var="saleboardView" value="${SaleboardView.getInstance()}" />
				<c:set var="list"
					value="${saleboardDao.getSaleboardListForBuyer(log.clientContact)}" />
				<form>
					<table>
						<thead>
							<tr>
								<th>판매자</th>
								<th>작성일자</th>
								<th>제목</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="saleboard" items="${list}">
								<c:set var="contact" value="${saleboard.sbSellClient}" />
								<c:set var="clientView" value="${ClientView.getInstance()}" />
								<tr>
									<td>${Tool.getClientNickname(contact)}</td>
									<td>${Tool.getTime(saleboard.sbPublishDate)}</td>
									<td><a href="viewSale?code=${saleboard.sbCode}">${saleboard.sbTitle}</a></td>
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
