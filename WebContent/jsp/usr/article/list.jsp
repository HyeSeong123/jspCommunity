<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Article"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Board"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 리스트</title>
</head>
<body>
	<h1>게시판 이름: ${board.getName()}</h1>

	<h2>게시물 리스트</h2>
	<a href="<c:url value="write?boardNum="/>${board.getBoardNum()}">글 쓰기</a> <br><br>

	<c:forEach var="article" items="${articles}"> 
		번호 : ${article.getNum()}<br>
		작성날짜 : ${article.getRegDate()}<br>
		갱신날짜 : ${article.getUpdateDate()}<br>
		작성자 : ${article.getExtra__writer()}<br>
		제목 : <a href="detail?num=${article.getNum()}"> ${article.getTitle()}</a> <hr>
	</c:forEach>
</body>
</html>