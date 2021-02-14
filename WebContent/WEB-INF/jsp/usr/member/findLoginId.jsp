<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="로그인아이디 찾기" />

<%@ include file="../../part/head.jspf"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<body>
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
	<section class="loginPage__ flex flex-jc-c">
		<section class="login-page find_login flex flex-column flex-ai-c">
			<div class="">유실물 센터</div>
			<form action="doFindLoginId" method="POST"
				onsubmit="DoFindLoginIdForm__submit(this); return false;">
				
				<input type="hidden" name="" />
				
				
				<div class="findLoginId__name form__input1">
					<input type="text" name="name" placeholder="이름을 입력해주세요" />
				</div>

				<div class="findLoginId__email form__input2">
					<input type="email" name="email" placeholder="이메일을 입력해주세요" />
				</div>
				
				<div class="findLoginId__buttons">
					<div class="findLoginId__submit">
						<input type="submit" value="아이디 찾기" />
					</div>
				</div>

				<div class="return_button">
					<button type="button" onclick="history.back()">되돌아가기</button>
				</div>
			</form>
		</section>
	</section>

	<%@ include file="../../part/foot.jspf"%>