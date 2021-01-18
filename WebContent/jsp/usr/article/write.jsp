<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Article"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Board"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<%@ include file="../../part/head.jspf" %>
<body>
	<h2> ${board.name} 게시물 추가</h2>

	<div>
		<form action="doWrite" method="POST">
		<input type="hidden" name="memberNum" value="1">
		<input type="hidden" name="boardNum" value="${board.num}">
		<input type="text" name="title" placeholder="제목을 입력해주세요">
		<hr />
		<textarea name="body" placeholder="내용을 입력해주세요."></textarea>
		<hr />
		<input type="submit" value="add">
		<button type="button" onclick="history.back()">뒤로가기</button>
		</form>
	</div>
<%@ include file="../../part/foot.jspf" %>