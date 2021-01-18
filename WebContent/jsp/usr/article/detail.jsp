<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Article"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../../part/head.jspf" %>
<body>
	<h1>게시판 이름: ${article.extra__boardName}</h1>
	<h2>게시물 이름</h2>
	<a onclick="if ( confirm('정말 삭제하시겠습니까?') == false) {return false; };"
		href="doDelete?num=${article.num}">글 삭제</a>
	<div>
		번호 : ${article.num}
		<br />
		작성날짜 : ${article.regDate}
		<br />
		갱신날짜 : ${article.updateDate}
		<br />
		작성자 : ${article.extra__writer}
		<br />
		제목 : ${article.title}
		<hr />
		내용 : ${article.body}
		<hr />
		<a href="modify?num=${article.num}">글 수정하기</a>
		<a href="list?boardNum=${article.boardNum}">목록으로 이동</a>
		<button type="button" onclick="history.back()">뒤로가기</button>
	</div>
<%@ include file="../../part/foot.jspf" %>