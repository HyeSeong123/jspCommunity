<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>
	<hr>
	<form action="doJoin" method="POST">
	<div> 이름 : </div>
		<input type="text" name="name">
	<div> 아이디 : </div>
	<input type="text" name="loginId">
	<button type="submit" value="doJoin">회원가입</button>
	<div> 패스워드 : </div>
	<input type="password" name="loginPw">
	<div> 닉네임 : </div>
	<input type="text" name="nickname">
	<div> 이메일 : </div>
	<input type="text" name="email">
	</form>
</body>
</html>