<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Article"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Article article = (Article) request.getAttribute("article");
%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 리스트</title>
</head>
<body>

	<h1>게시판 이름: ${article.getExtra__boardName()}</h1>
	<h2>게시물 이름</h2>
	<a onclick="if ( confirm('정말 삭제하시겠습니까?') == false) {return false; };"
		href="doDelete?num=${article.getNum()}">글 삭제</a>
	<div>
		번호 : ${article.getNum()}
		<br />
		작성날짜 : ${article.getRegDate()}
		<br />
		갱신날짜 : ${article.getUpdateDate()}
		<br />
		작성자 : ${article.getExtra__writer()}
		<br />
		제목 : ${article.getTitle()}
		<hr />
		내용 : ${article.getBody()}
		<hr />
		<a href="modify?num=${article.getNum()}">글 수정하기</a>
		<a href="list?boardNum=${article.getBoardNum()}">목록으로 이동</a>
		<button type="button" onclick="history.back()">뒤로가기</button>
	</div>

</body>
</html>