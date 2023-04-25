<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="client.*" %>
<%@ page import="saleboard.*" %>
<%@ page import="client.controller.*" %>
<%@ page import="saleboard.controller.*" %>
   
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
    <c:set var="contact" value="${param.contact }" scope="page" />
    <c:set var="clientDao" value="${ClientDao.getInstance() }" />
    <c:set var="client" value="${clientDao.getClientByContact(contact) }" scope="page" />
    <c:set var="saleboardDao" value="${SaleboardDao.getInstance() }" />
  
    <div class="seller">
      <h2>${client.getClientNickname() }님의 판매상품</h2>
    </div>
    <div class="saleboard">
      <c:set var="list" value="${saleboardDao.getSaleboardList(contact)}" />
      <c:forEach var="board" items="${list}">
        <c:set var="sbSellClient" scope="session" value="${board.sbSellClient}" />
        <c:set var="sbCode" scope="session" value="${board.sbCode }" />
        <a href="viewSale?code=${board.sbCode}">
          <div class="cards">
            <div class="imgBox">
              <img src="${board.sbImg}>" />
            </div>
            <div class="content">
              <p><c:out value="${board.sbTitle}" /></p>
              <p><c:out value="${board.sbPrice}" />원</p>
              <p><c:out value="${user.clientAddress}" /></p>
              <p>관심 <c:out value="${saleboardDao.countOfInterestsBySbCode(sbCode)}" /></p>
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