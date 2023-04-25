<%@page import="boardCateogry.BoardCategory"%>
<%@page import="board.Board"%>
<%@page import="board.controller.BoardDao"%>
<%@page import="category.Category"%>
<%@page import="category.controller.CategoryDao"%>
<%@page import="client.Client"%>
<%@page import="java.util.List"%>
<%@page import="client.controller.ClientDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="clientDao" value="<%=ClientDao.getInstance()%>"/>
	<c:set var="categoryDao" value="<%=CategoryDao.getInstance()%>"/>
	<c:set var="boardDao" value="<%=BoardDao.getInstance()%>"/>
	
	<c:set var="clientList" value="${clientDao.getClientAll()}"/>
	<c:set var="cateList" value="${categoryDao.getCategoryAll()}"/><%-- 
	<c:set var="boardList" value="${boardDao.getBoardAll()}"/>
	<c:set var="bcateList" value="${categoryDao.getBoardCategoryAll()}"/> --%>
	
	<table>
		<c:forEach var="client" items="${clientList}">
			<tr>
				<th><c:out value="${client.getClientContact()}"/></th>
				
				<th><c:out value="${client.getClientPassword()}"/></th>
				<th><c:out value="${client.getClientNickname()}"/></th>
				<th><c:out value="${client.getClientPostcode()}"/></th>
				<th><c:out value="${client.getClientAddress()}"/></th>
				<th><c:out value="${client.getClientY()}"/></th>
				<th><c:out value="${client.getClientX()}"/></th>
				<th><c:out value="${client.getClientJoindate()}"/></th>
				<th><c:out value="${client.getClientAdmin()}"/></th>
				
			</tr>
		</c:forEach>
		<c:forEach var="category" items="${cateList}">
			<tr>
				<th><c:out value="${category.getCateCode()}"/></th>
				<th><c:out value="${category.getCateName()}"/></th>
			</tr>
		</c:forEach>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<th><c:out value="${board.getBoardCode()}"/></th>
				<th><c:out value="${board.getBoardTitle()}"/></th>
				<th><c:out value="${board.getBoardContent()}"/></th>
				<th><c:out value="${board.getBoardRegdate()}"/></th>
			</tr>
		</c:forEach>
		<%-- <c:forEach var="bcate" items="${bcateList}">
			<tr>
				<th><c:out value="${bcate.getBcateCode()}"/></th>
				<th><c:out value="${bcate.getBcateName()}"/></th>
			</tr>
		</c:forEach> --%>
	</table>
</body>
</html>
