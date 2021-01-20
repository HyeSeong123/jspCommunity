<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${board.name}" />

<%@ include file="../../part/head.jspf"%>
<body>
	<h1>게시판 이름: ${pageTitle}</h1>
	<h2>게시물 리스트</h2>
	<a href="../home/main">메인</a>
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
	<%@ include file="../../part/foot.jspf"%>