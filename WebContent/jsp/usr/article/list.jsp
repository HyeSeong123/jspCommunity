<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${board.name}" />

<%@ include file="../../part/head.jspf"%>
<body>

	<h1>게시판 이름: ${pageTitle}</h1>
	
	<a href="../home/main">메인</a>
	<a href="<c:url value="write?boardNum="/>${param.boardNum}">글 쓰기</a>
	<br>
	<br>
	<section class="info-list">
	<ul class="news-list">
	<c:forEach var="article" items="${articles}">
		<li class="determine-hover enable-hover" data-type="article">
		<a class="hystory-link" href="detail?num=${article.num}" title="${article.title}" >
			<div class="canvas-background">
				<div class="stalker-wrap"></div>
			</div>
			<div class="gradient-hover gradient-1"></div>
			<div class="constrain">
				<span class="date">${article.regDate}</span>
				<span class="writer">${article.extra__writer}</span>
				<p class="title">${article.title}</p>
				<div class="like_unLike">
				<span class="like"><i class="far fa-heart"></i> ${article.like}</span>
				<span class="unLike"><i class="fas fa-thumbs-down"></i> ${article.unLike}</span>
				</div>
			</div>
			<div class="gradient-hover gradient-2"></div>
		</a>
		</li> 
	</c:forEach>
	</ul>
	</section>
	<div class="pagebox-cover flex flex-jc-c">
		<c:if test= "${totalCount != 0 && page != 1}">
		<c:set var="href"
			value="?page=1&boardNum=${param.boardNum}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
		<a href="${href}">◀◀</a>
		</c:if>
		<c:if test="${pageBoxStartBeforeBtnNeedToShow}">
			<c:set var="href"
				value="?page=${pageBoxStartBeforePage}&boardNum=${param.boardNum}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<a href="${href}">◀</a>
		</c:if>
		<c:forEach var="i" begin="${pageBoxStartPage}" end="${pageBoxEndPage}"
			step="1">
			<c:set var="aClass" value="${param.page == i ? 'red' : ''}" />
			<c:set var="href"
				value="?page=${i}&boardNum=${param.boardNum}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<a class="${aClass}" href="${href}">${i}</a>
		</c:forEach>
		
		<c:if test="${pageBoxEndAfterBtnNeedToShow}">
			<c:set var="href"
				value="?page=${pageBoxEndAfterPage}&boardNum=${param.boardNum}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<a href="${href}">▶</a>
		</c:if>
		
		<c:if test= "${totalCount != 0 && page != totalPage}">
		<c:set var="href"
			value="?page=${totalPage}&boardNum=${param.boardNum}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
		<a href="${href}">▶▶</a>
		</c:if>
	</div>

	<%@ include file="../../part/foot.jspf"%>