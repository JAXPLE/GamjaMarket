<%@page import="Tool.Tool"%>
<%@page import="client.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="../resources/css/form.css">
</head>
<body>
	<div class="container">
		<c:import url="header" />
			<section>
					<div class="box">
					    <c:if test="${not empty sessionScope.log}">
					        <h2>개인정보수정</h2>
					        <form method="post" action="../service">
					            <div class="info">
					                <input type="hidden" name="command" value="modifyClient"> 
					                <c:set var="clientDao" value="${ ClientDao.getInstance()}" />
									<c:set var="nicknameList" value="${clientDao.nicknameAll}" />
					                 <input type="hidden" name="nicknameList" id="nicknameList" value="${nicknameList}" />
					                <input type="text" name="contact" required value="${sessionScope.log.clientContact}" readonly><p id="message"> 연락처는 수정이 불가능합니다.</p>
					                <input type="password" id="password" name="password" required placeholder="비밀번호"><br> 
					                <input type="password" id="passwordcheck" name="passwordcheck" required placeholder="비밀번호재확인"><p id="passmessage"></p> 
					                <input type="text" id="nickname" name="nickname" required value="${sessionScope.log.clientNickname}"><p id="nickmessage"></p> 
					                <div>
					                <input type="text" name="postcode" id="postcode" required value="${sessionScope.log.clientPostcode}">
					                <input id="search-postcode" type="button" onclick="execDaumPostcode()" value="우편번호 찾기" />
					                </div>
					                <div>
					                <input type="text" name="address" id="address" required value="${sessionScope.log.clientAddress}"><br> 
					                </div>
					                <input type="date" name="joindate" value=${Tool.getTime(sessionScope.log.clientJoindate)} readonly><br> 
					                 <input type="hidden" name="y" id="y" value=${sessionScope.log.clientY }>
									  <input type="hidden" name="x" id="x" value=${sessionScope.log.clientX }>
					            <div class="buttons">
					                <input type="button" value="수정" onclick="checkValues(form)">			
					                <input type="button" value="탈퇴" onclick="location.href='leave'">						
					                <input type="button" value="뒤로가기" onclick="location.href='clientPageList'">						
					            </div>
					            </div>
					        </form>
					    </c:if>
					</div>
			</section>
		<c:import url="footer" />
	</div>
</body>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../resources/js/searchaddress.js"></script>
<script src="../resources/js/modifyValidation.js"></script>
</html>