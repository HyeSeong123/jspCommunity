<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="로그인" />

<%@ include file="../../part/head.jspf"%>
<body>
	<h1>${pageTitle}</h1>
	<hr>
	<div>
		<script>
			function DoLoginForm__submit(form) {
				let DoLoginForm_submited = false;

				if (DoLoginForm_submited) {
					alert('처리중입니다.');
					return;
				}

				form.loginId.value = form.loginId.value.trim();

				if (form.loginId.value.length == 0) {
					alert('아이디를 입력해주세요.');
					form.loginId.focus();
					return;
				}

				form.loginPw.value = form.loginPw.value.trim();

				if (form.loginPw.value.length == 0) {
					alert('패스워드를 입력해주세요.');
					form.loginPw.focus();
					return;
				}

				form.submit();
				DoLoginForm_submited = true;
			}
		</script>
		<form action="doLogin" method="POST"
			onsubmit="DoLoginForm__submit(this); return false;">
			<div>아이디 :</div>
			<div>
				<input type="text" name="loginId" />
			</div>
			<div>패스워드 :</div>
			<div>
				<input type="password" name="loginPw" />
			</div>
			<hr />
			<div>
				<input type="submit" value="로그인" />
				<button type="button" onclick="history.back()">뒤로가기</button>
			</div>
		</form>
	</div>
	<a href="join">회원 가입</a>
	
<%@ include file="../../part/foot.jspf"%>