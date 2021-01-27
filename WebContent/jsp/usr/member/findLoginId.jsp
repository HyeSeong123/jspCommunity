<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="로그인아이디 찾기" />

<%@ include file="../../part/head.jspf"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<body>
	<section class="login-page height-100p flex flex-jc-c">
		<script>
			function DoFindLoginIdForm__submit(form) {
				let DoFindLoginIdForm_submited = false;

				if (DoFindLoginIdForm_submited) {
					alert('처리중입니다.');
					return;
				}

				form.name.value = form.name.value.trim();

				if (form.name.value.length == 0) {
					alert('이름을 입력해주세요.');
					form.name.focus();
					return;
				}

				form.email.value = form.email.value.trim();

				if (form.email.value.length == 0) {
					alert('이메일을 입력해주세요.');
					form.email.focus();
					return;
				}

				form.submit();
				DoFindLoginIdForm_submited = true;
			}
		</script>
		<section class="form__login-box height-100p">
			<div class="logo form__logo">유실물 센터</div>
			<form action="doFindLoginId" method="POST"
				onsubmit="DoFindLoginIdForm__submit(this); return false;">
				
				<div class="input__findLogin-form findLogin_input input_opt1">
					<input type="text" name="name" placeholder="이름을 입력해주세요" />
				</div>

				<div class="input__findLoginEmail-form findLogin_input input_opt1">
					<input type="email" name="email" placeholder="가입시 입력한이메일을 입력해주세요" />
				</div>
				<div class="findLogin__buttons">
					<div class="findLogin__find-button input_opt2">
						<input type="submit" value="아이디 찾기" />
					</div>
				</div>
				
				<div class="login__back-button">
					<button type="button" onclick="history.back()">되돌아가기</button>
				</div>
			</form>
		</section>
	</section>

	<%@ include file="../../part/foot.jspf"%>