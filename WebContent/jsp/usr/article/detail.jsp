<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Article"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>

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
	<h1>게시판 이름: <%= article.extra__boardName %> </h1>
	<h2>게시물 이름</h2>

	<div>
		번호 :
		<%=article.num%>
		<br />
		작성날짜 :
		<%=article.regDate%>
		<br />
		갱신날짜 :
		<%=article.updateDate%>
		<br />
		작성자 :
		<%=article.extra__writer%>
		<br />
		제목 :
		<%=article.title%>
		<hr />
	</div>

</body>
</html>