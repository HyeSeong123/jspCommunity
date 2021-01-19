<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="${board.name}" />

<%@ include file="../../part/head.jspf"%>
<body>
	<h2>${pageTitle} 게시물 추가</h2>

	<div>
		<form action="doWrite" method="POST">
			<input type="hidden" name="memberNum" value="1"> <input
				type="hidden" name="boardNum" value="${board.boardNum}"> <input
				type="text" name="title" placeholder="제목을 입력해주세요">
			<hr />
			<textarea name="body" placeholder="내용을 입력해주세요."></textarea>
			<hr />
			<input type="submit" value="add">
			<button type="button" onclick="history.back()">뒤로가기</button>
		</form>
	</div>
	<%@ include file="../../part/foot.jspf"%>