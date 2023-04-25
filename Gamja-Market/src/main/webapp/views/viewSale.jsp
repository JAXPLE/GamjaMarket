<%@page import="Tool.Tool"%>
<%@page import="interests.controller.InterestsDao"%>
<%@page import="client.controller.ClientDao"%>
<%@page import="client.Client"%>
<%@page import="saleboard.controller.SaleboardDao"%>
<%@page import="saleboard.Saleboard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/css/grid.css">
<link rel="stylesheet" href="../resources/css/viewSale.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<c:import url="header" />
		<section>
			<c:set var="sbCode" value="${param.code}" />
			<c:set var="saleboardDao" value="${SaleboardDao.getInstance()}" />
			<c:set var="board" value="${saleboardDao.getSaleboardByCode(sbCode)}" />
			<c:if test="${empty board}">
				<c:redirect url="saleboard" />
			</c:if>
			<div class="product">
				<div class="img">
					<img class="productImg" src="${board.sbImg}" />
				</div>
				<div class="clientInfo">
					<c:set var="clientDao" value="${ClientDao.getInstance()}" />
					<c:set var="client"
						value="${clientDao.getClientByContact(board.sbSellClient)}" />
					${client.clientNickname}
				</div>
				<div class="detail">
					<h3>${board.sbTitle}</h3>
					<h4>${board.sbPrice}원</h4>
					<p>${board.sbContent}</p>
				</div>
				<div class="setInterests">
					<c:set var="log" value="${sessionScope.log}" />
					<c:set var="clientContact" value="${null}" />
					<c:set var="isInterested" value="${false}" />

					<c:if test="${not empty log}">
						<c:set var="clientContact" value="${log.clientContact}" />
						<c:set var="interestsDao" value="${InterestsDao.getInstance()}" />
						<c:set var="isInterested"
							value="${interestsDao.getInterests(sbCode, clientContact)}" />
					</c:if>

					<input type="hidden" id="code" value="${sbCode}">
					<c:choose>
						<c:when test="${isInterested}">
							<i id="isInterested" class="fi fi-sr-heart"
								onclick="${empty log ? 'location.href=\'login\'' : 'checkHeart();'}"></i>
						</c:when>
						<c:otherwise>
							<i id="isInterested" class="fi fi-rs-heart"
								onclick="${empty log ? 'location.href=\'login\'' : 'checkHeart();'}"></i>
						</c:otherwise>
					</c:choose>
					<span>관심목록에 추가</span> 
					<span class="countOfInterests">관심${saleboardDao.countOfInterestsBySbCode(sbCode)}</span>
				</div>
				<c:if test="${not empty log}">
					<input type="button" id="chat-button"
						onclick="sendChatInfo(${board.sbCode}, '${log.clientNickname}', '${Tool.getClientNickname(board.sbSellClient)}', '${board.sbTitle}')"
						value="채팅하기">
				</c:if>
			</div>
			<div class="buttonArea">
				<button class="button" onclick="location.href='saleboard'">목록으로</button>
				<c:if
					test="${not empty log and log.clientContact eq board.sbSellClient}">
					<button class="button"
						onclick="location.href='saleForm?code=${board.sbCode}'">수정하기</button>
					<button class="button"
						onclick="location.href='service?command=deleteSell&amp;code=${board.sbCode}'">삭제하기</button>
				</c:if>
			</div>
		</section>
		<c:import url="footer" />
	</div>
	<script type="text/javascript" src="resources/js/sendChatInfo.js"></script>
	<script type="text/javascript" src="resources/js/checkInterested.js"></script>
</body>
</html>