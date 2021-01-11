<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Article"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>

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
	<h2>게시물 추가</h2>

	<div>
		<form action="http://localhost:8080/jspCommunity/usr/article/action/add" target="_blank">
		<input type="hidden" name="memberNum" value="1">
		<input type="hidden" name="boardNum" value="1">
		<input type="text" name="title" placeholder="제목을 입력해주세요">
		<hr />
		<textarea name="body" placeholder="내용을 입력해주세요."></textarea>
		<hr />
		<input type="submit" value="add">
		</form>
	</div>
</body>
</html>