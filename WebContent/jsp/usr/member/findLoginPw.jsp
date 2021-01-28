<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="비밀번호 찾기" />

<%@ include file="../../part/head.jspf"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<body>
	<section class="login-page height-100p flex flex-jc-c">
		<script>
			function DoFindLoginPwForm__submit(form) {
				let DoFindLoginPwForm_submited = false;

				if (DoFindLoginPwForm_submited) {
					alert('처리중입니다.');
					return;
				}

				form.loginId.value = form.loginId.value.trim();

				if (form.loginId.value.length == 0) {
					alert('아이디를 입력해주세요.');
					form.loginId.focus();
					return;
				}

				form.email.value = form.email.value.trim();

				if (form.email.value.length == 0) {
					alert('이메일을 입력해주세요.');
					form.email.focus();
					return;
				}

				form.submit();
				DoFindLoginPwForm_submited = true;
			}
		</script>
		<section class="form__login-box height-100p">
			<div class="logo form__logo">유실물 센터</div>
			<form action="doFindLoginPw" method="POST"
				onsubmit="DoFindLoginPwForm__submit(this); return false;">
				
				<div class="input__findLogin-form findLogin_input input_opt1">
					<input type="text" name="loginId" placeholder="아이디를 입력해주세요" />
				</div>

				<div class="input__findLoginEmail-form findLogin_input input_opt1">
					<input type="email" name="email" placeholder="회원님의 이메일주소를 입력해주세요" />
				</div>
				<div class="findLogin__buttons">
					<div class="findLogin__find-button input_opt2">
						<input type="submit" value="비밀번호 찾기" />
					</div>
				</div>
				
				<div class="login__back-button">
					<button type="button" onclick="history.back()">되돌아가기</button>
				</div>
			</form>
		</section>
	</section>

	<%@ include file="../../part/foot.jspf"%>