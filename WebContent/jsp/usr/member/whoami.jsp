<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="내정보" />

<%@ include file="../../part/head.jspf"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<body>
	<h1>${pageTitle}</h1>
	<div>
		<div class="회원 번호">회원번호 : ${memberNum}</div>
		<div class="회원 이름">회원이름 : ${name}</div>
		<div class="닉네임">닉네임 : ${nickName}</div>
		<div class="전화번호">휴대전화 번호 : ${phNum}</div>
		<div class="아이디">아이디 : ${loginId}</div>
		<div class="이메일">이메일: ${email}</div>
		<div class="생성 일자">계정 생성 일자 : ${regDate}</div>
		<div class="수정 일자">계정 정보 수정 일자 : ${updateDate}</div>
	</div>
	<div>
		<a href="modifyAccount">정보 수정</a>
	</div>
	<%@ include file="../../part/foot.jspf"%>