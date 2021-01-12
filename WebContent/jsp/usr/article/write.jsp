<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Article"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Board"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>

<%
	Board board = (Board) request.getAttribute("board");
%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title><%= board.name %>)게시물 작성페이지</title>
</head>
<body>
	<h2><%= board.name %>)게시물 추가</h2>

	<div>
		<form action="doWrite" method="POST">
		<input type="hidden" name="memberNum" value="1">
		<input type="hidden" name="boardNum" value="<%=board.boardNum%>">
		<input type="text" name="title" placeholder="제목을 입력해주세요">
		<hr />
		<textarea name="body" placeholder="내용을 입력해주세요."></textarea>
		<hr />
		<input type="submit" value="add">
		<button type="button" onclick="history.back()">뒤로가기</button>
		</form>
	</div>
</body>
</html>