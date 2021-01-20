<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="메인화면" />

<%@ include file="../../part/head.jspf"%>
<body>
	<h1>게시판 이름: ${pageTitle}</h1>

	<a href="../article/list?boardNum=1">공지사항</a>
	<a href="../article/list?boardNum=2">방명록</a>
	<a href="../article/list?boardNum=3">자유게시판</a>


	<%@ include file="../../part/foot.jspf"%>