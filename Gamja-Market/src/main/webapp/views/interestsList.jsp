<%@page import="saleboard.controller.SaleboardDao"%>
<%@page import="client.controller.ClientDao"%>
<%@page import="saleboard.Saleboard"%>
<%@page import="interests.Interests"%>
<%@page import="java.util.ArrayList"%>
<%@page import="interests.controller.InterestsDao"%>
<%@page import="client.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/saleboard.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
			<h2>내 관심목록</h2>
			<div class="saleboard">
				<c:set var="client" value="${sessionScope.log}" />
				<c:set var="interest" value="${InterestsDao.getInstance()}" />
				<c:set var="clientDao" value="${ClientDao.getInstance()}" />
				<c:set var="saleboardDao" value="${SaleboardDao.getInstance()}" />
				<c:set var="list"
					value="${interest.getInterestsListByContact(client.clientContact)}" />
				<c:forEach var="i" items="${list}">
					<c:set var="sbSellClient" value="${i.sbSellClient}" />
					<c:set var="user"
						value="${clientDao.getClientByContact(sbSellClient)}" />
					<a href="viewSale?code=${i.sbCode}">
						<div class="cards">
							<div class="imgBox">
								<img src="${i.sbImg}" />
							</div>
							<div class="content">
								<p>${i.sbTitle}</p>
								<p>${i.sbPrice}원</p>
								<p>${user.clientAddress}</p>
								<p>관심 ${saleboardDao.countOfInterestsBySbCode(i.sbCode)}</p>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
		</section>
		<c:import url="footer" />
	</div>
</body>
</html>