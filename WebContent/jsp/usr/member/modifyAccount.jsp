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
		<form>
			<input hidden="memberNum">
			<div class="닉네임">
				닉네임 : <input name="nickName" type="text" />
			</div>
			<div class="이메일">
				이메일: <input name="email" type="text" />
			</div>
			<div class="패스워드">
				패스워드: <input name="loginPw" type="password" />
			</div>
			<div class="패스워드확인">
				패스워드 확인: <input name="confirmPw" type="password" />
			</div>
			<div>
			<input type="submit" value="가입" />
			<button type="button" onclick="history.back()">뒤로가기</button>
			</div>
		</form>
	</div>


	<%@ include file="../../part/foot.jspf"%>