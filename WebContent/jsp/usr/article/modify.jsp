<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Article"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Board"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../../part/head.jspf" %>
	<h2>${article.title}))게시물 수정</h2>

	<div>
		<span>원본 내용</span>
		<div>제목: ${article.title}</div>
		<div>내용: ${article.body}</div>
	</div>
	<br>

	<div>
		<span>수정</span> <br> <br>
		<form action="doModify" method="POST">
			<input type="hidden" name="memberNum" value="1">
			<input type="hidden" name="num" value="${article.num}">
			<input type="text" name="title" placeholder="수정할 제목을 입력해주세요">
			<hr>
			<textarea name="body" placeholder="수정할 내용을 입력해주세요."></textarea>
			<hr>
			<input type="submit" value="변경">
			<button type="button" onclick="history.back()">뒤로가기</button>
		</form>
	</div>
<%@ include file="../../part/foot.jspf" %>