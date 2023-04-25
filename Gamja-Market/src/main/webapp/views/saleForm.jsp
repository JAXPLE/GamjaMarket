<%@page import="category.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="category.controller.CategoryDao"%>
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
<link rel="stylesheet" href="../resources/css/form.css">
<link rel="stylesheet" href="../resources/css/saleForm.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<c:import url="/header" />
		<section>
			<c:set var="client" value="${sessionScope.log}" />

			<c:if test="${client == null}">
				<c:redirect url="saleboard" />
			</c:if>

			<c:set var="saleboardDao" value="${SaleboardDao.getInstance()}" />
			<c:set var="saleboard" value="${null}" />
			<c:set var="code" value="${param.code}" />
			<c:set var="sbCode" value="-1" />

			<c:if test="${code ne null}">
				<c:set var="sbCode" value="${code}" />
				<c:set var="saleboard"
					value="${saleboardDao.getSaleboardByCode(sbCode)}" />
			</c:if>

			<div class="formBox">
				<form action="service" method="post">
					<input type="hidden" name="sbCode"
						value="${sbCode == -1 ? saleboardDao.getMaxOfSaleboardCode() : sbCode}">
					<input type="hidden" name="command"
						value="${sbCode == -1 ? 'sale' : 'modifySell'}"> <input
						type="hidden" name="sbSellClient" value="${client.clientContact}">

					<div class="inputBox">
						<div class="category">
							<c:set var="categoryDao" value="${CategoryDao.getInstance()}" />
							<c:set var="list" value="${categoryDao.getCategoryAll()}" />
							<c:set var="sbTitle" value="${param.sbTitle}" />
							<c:set var="sbPrice" value="${param.sbPrice}" />
							<c:set var="sbContent" value="${param.sbContent}" />

							<select name="cateCode">
								<c:forEach items="${list}" var="category">
									<option value="${category.cateCode}" ${sbCode == -1 ? '' : (category.cateCode == saleboard.cateCode ? 'selected' : '')}>${category.cateName}</option>
								</c:forEach>
							</select>
						</div>

						<div class="sbImg">
							<input type="file" id="fileInput" onchange="uploadImage();">
							<input type="hidden" name="sbImg" id="sbImg">
						</div>

						<div class="sbTitle">
							<input type="text" name="sbTitle" id="sbTitle"
								placeholder="제목을 입력해주세요."
								value="${sbCode == -1 ? '' : saleboard.sbTitle}${sbTitle == null ? '' : sbTitle}">
						</div>

						<div class="sbPrice">
							<input type="text" name="sbPrice" id="sbPrice"
								placeholder="가격을 숫자로 입력해주세요."
								value="${sbCode == -1 ? '' : saleboard.sbPrice}${sbPrice == null ? '' : sbPrice}">
							원
						</div>

						<div class="sbContent">
							<textarea name="sbContent" wrap="soft"
								placeholder="게시글 내용을 작성해주세요." id="sbContent">${sbCode == -1 ? '' : saleboard.sbContent}${sbContent == null ? '' : sbContent}</textarea>
						</div>
					</div>
					<div class="previewBox">
						<img id="preview">
					</div>
					<div class="buttonBox">
						<input type="button" class="button" value="작성하기"
							onclick="checkBoardValues(form);">
					</div>
				</form>
			</div>

		</section>
		<c:import url="/footer" />
	</div>
	<script type="text/javascript"
		src="../resources/js/saleboardValidation.js"></script>
	<script type="text/javascript" src="../resources/js/uploadFile.js"></script>
</body>
</html>