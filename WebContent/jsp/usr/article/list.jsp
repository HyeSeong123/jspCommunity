<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${board.name}" />

<%@ include file="../../part/head.jspf"%>
<body>

	<h1>게시판 이름: ${pageTitle}</h1>
	<h2>게시물 리스트</h2>
	<a href="../home/main">메인</a>
	<a href="<c:url value="write?boardNum="/>${param.boardNum}">글 쓰기</a>
	<br>
	<br>
	<c:forEach var="article" items="${articles}"> 
		번호 : ${article.num}<br>
		작성날짜 : ${article.regDate}<br>
		갱신날짜 : ${article.updateDate}<br>
		작성자 : ${article.extra__writer}<br>
		제목 : <a href="detail?num=${article.num}"> ${article.title}</a>
		<hr>
	</c:forEach>

	<div>
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