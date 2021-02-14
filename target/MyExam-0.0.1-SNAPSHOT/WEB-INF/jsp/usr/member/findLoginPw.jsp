<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="비밀번호 찾기" />

<%@ include file="../../part/head.jspf"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<body>
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
	<section class="loginPage__ flex flex-jc-c">
		<section class="login-page flex flex-column flex-ai-c">
			<div class="logo form__logo">유실물 센터</div>
			<form action="doFindLoginPw" method="POST"
				onsubmit="DoFindLoginPwForm__submit(this); return false;">
				
				<input type="hidden" name="" />
				
				<div class="input__findLogin-form form__input1">
					<input type="text" name="loginId" placeholder="아이디를 입력해주세요" />
				</div>

				<div class="input__findLoginEmail-form form__input2">
					<input type="email" name="email" placeholder="이메일주소를 입력해주세요" />
				</div>
				<div class="findLogin__buttons">
					<div class="findLoginId__submit">
						<input type="submit" value="비밀번호 찾기" />
					</div>
				</div>

				<div class="return_button">
					<button type="button" onclick="history.back()">되돌아가기</button>
				</div>
			</form>
		</section>
	</section>

	<%@ include file="../../part/foot.jspf"%>