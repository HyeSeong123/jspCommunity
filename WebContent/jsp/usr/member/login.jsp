<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="로그인" />

<%@ include file="../../part/head.jspf"%>
<body>
	<section class="login-page height-100p flex flex-jc-c">
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
		<section class="form__login-box height-100p">
			<div class="logo form__logo">주차장</div>
			<form action="doLogin" method="POST"
				onsubmit="DoLoginForm__submit(this); return false;">
				<div class="input__login-form login_input">
					<input type="text" name="loginId" placeholder="아이디 입력" />
				</div>

				<div class="input__password-form login_input">
					<input type="password" name="loginPw" placeholder="비밀번호 입력" />
				</div>
				<div class="login__buttons">
					<div class="login__login-button">
						<input type="submit" value="로그인" />
					</div>
					<div class="login__back-button">
						<button type="button" onclick="history.back()">뒤로가기</button>
					</div>
				</div>
			</form>
		</section>
	</section>

	<%@ include file="../../part/foot.jspf"%>