<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="메인화면" />

<%@ include file="../../part/head.jspf"%>
<body>
	<h1>게시판 이름: ${pageTitle}</h1>
	<a href="../article/list?boardNum=1">게시물 리스트1</a>
	<a href="../article/list?boardNum=2">게시물 리스트2</a>
	<a href="../article/list?boardNum=3">게시물 리스트3</a>
	<c:if test="${sessionScope.loginedMemberId == null}">
		<a href="../member/join">회원 가입</a>
		<a href="../member/login">로그인</a>
	</c:if>

	<c:if test="${sessionScope.loginedMemberId > 0}">
		<a href="../member/doLogout">로그아웃</a>
	</c:if>

	<%@ include file="../../part/foot.jspf"%>