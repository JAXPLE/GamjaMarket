<%@page import="saleboard.controller.SaleboardDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.controller.BoardDao"%>
<%@page import="client.Client"%>
<%@page import="client.controller.ClientDao"%>
<%@page import="Tool.Tool" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/css/saleboard.css">
<link rel="stylesheet" href="../resources/css/board-main.css">
<title>감자마켓</title>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=18e28234826f09976803b8f1a8c75315&libraries=services,clusterer,drawing"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=18e28234826f09976803b8f1a8c75315"></script>
</head>
<body>
	<div class="container">
		<c:import url="header" />
				<c:set var="client" value="${sessionScope.log }"></c:set>
		<section id="index-section">
			<div id="map-area">
				<h1>내 주변의 감자마켓</h1>
				<p id=info>
					<br>중고 거래부터 동네 정보까지, 이웃과 함께해요<br>가깝고 따뜻한 당신의 근처를 만들어요
				</p>
				<br>
				<div id="map" style="width: 400px; height: 350px;"></div>
					<input type="hidden" name="clientX" id="clientX">
					<input type="hidden" name="clientY" id="clientY">
			</div>
			<div id="sellPic">
				<img src="../resources/img/sellpicture.png">
			</div>
		</section>
		<section id="popular-section">
			<c:set var="saleboardDao" value="${SaleboardDao.getInstance() }" />
			<h1>중고거래 인기매물</h1>
			<p id=info>
				<br>우리 동네 인기 매물을 알아보세요!
			</p>
			<div class="saleboard">
				<c:choose>
					<c:when test="${empty client }">
						<c:set var="list"
							value="${saleboardDao.getPopularSaleboardList() }"/>
					</c:when>
					<c:otherwise>
						<c:set var="list"
							value="${saleboardDao.getPopularSaleboardList(client) }"/>
					</c:otherwise>
				</c:choose>
				<c:forEach var="board" items="${list }">
					<div class="cards">
						<div class="imgBox">
							<img src="${board.sbImg }" />
						</div>
						<div class="content">
							<p>${board.sbTitle}</p>
							<p>${board.sbPrice}원</p>
							<p>${user.clientAddress}</p>
							<p>관심 ${saleboardDao.countOfInterestsBySbCode(board.sbCode)}</p>
						</div>
					</div>
				</c:forEach>
			</div>
			<p>
				<a href="saleboard">더 많은 상품 보기-></a>
			</p>
		</section>
		<section id="board-section">
			<c:set var="boardDao" value="${BoardDao.getInstance() }" ></c:set>
			<h1>이웃과 함께 하는 동네생활</h1>
			<p id=info>
				우리 동네의 다양한 이야기를 이웃과 함께 나누어요.
			</p>
			<div class="board-main">
				<c:set var="bList" value="${boardDao.getBoardListMain() }" ></c:set>
				<c:forEach var="board" items="${bList }">
					<div class="cards">
						<div class="content" id="board-cards">
							<div>
							<h3>${board.bTitle }</h3>
							<p class="boardContent"  id="bContent">${board.bContent }</p>
							</div>
							<p>${Tool.getClientNickname(board.bWriter) }</p>
						</div>
					</div>
				</c:forEach>
			</div>
			<p>
				<a href="board">더 많은 게시글 보기-></a>
			</p>
		</section>
		<section id="chat-section">
		<div id="chat-info">
			<h1>구매하려면 동네 이웃과 대화해보세요</h1>
			<p id=info>
				<br>가까운 동네 주민들과 채팅으로 거래해봐요
			</p>
		</div>
			<div id="chatPic">
				<img src="../resources/img/chat.png">
			</div>
			<p id="go-chat">
				<a href="chatList">이웃들과 채팅하기-></a>
			</p>
		</section>
		<c:import url="footer" />
	</div>
	<script src="../resources/js/address.js"></script>
</body>
</html>