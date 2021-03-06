<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="내 정보 수정" />

<%@ include file="../../part/head.jspf"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<body>
	<h1>${pageTitle}</h1>
	<div>
		<script>
			let doModifyForm_submited = false;
			let doModifyForm_checkedLoginId = "";

			function DoModifyForm__submit(form) {

				if (doModifyForm_submited) {
					alert('처리중입니다.');
					return;
				}

				form.loginPw.value = form.loginPw.value.trim();
				if (form.loginPw.value.length > 0) {
					if (form.loginPw.value.length == 0) {
						alert('로그인 패스워드를 입력해주세요.');
						form.loginPw.focus();
						return;
					} else if (form.loginPw.value.length <= 6) {
						alert('패스워드는 6글자 이상이어야 합니다.')
						form.loginPw.focus();
						return;
					}

					form.confirmPw.value = form.confirmPw.value.trim();

					if (form.confirmPw.value.length == 0) {
						alert('패스워드 확인을 입력해주세요.');
						form.confirmPw.focus();
						return;
					}

					if (form.loginPw.value != form.confirmPw.value) {
						alert('패스워드와 패스워드 확인을 일치 시켜주세요');
						form.loginPw.focus();

						return;
					}
				}

				form.nickName.value = form.nickName.value.trim();

				if (form.nickName.value.length == 0) {
					alert('닉네임을 입력해주세요.');
					form.nickName.focus();
					return;
				}

				form.email.value = form.email.value.trim();

				if (form.email.value.length == 0) {
					alert('이메일을 입력해주세요.');
					form.email.focus();
					return;
				}

				form.phNum.value = form.phNum.value.trim();

				if (form.phNum.value.length == 0) {
					alert('핸드폰 번호를 입력해주세요.');
					form.phNum.focus();
					return;
				}

				if (form.loginPw.value.length > 0) {
					form.loginPwReal.value = sha256(form.loginPw.value);
					form.loginPw.value = "";
					form.confirmPw.value = "";
				}

				form.submit();
				doModifyForm_submited = true;
			}
		</script>

		<form action="doModifyAccount" method="POST"
			onsubmit="DoModifyForm__submit(this); return false;">

			<input type="hidden" name="loginPwReal" />
			<div class="비밀번호__변경">
				변경할 비밀번호 : <input name="loginPw" type="password" />
			</div>
			<div class="비밀번호__변경확인">
				비밀번호 확인 : <input name="confirmPw" type="password" />
			</div>
			<div class="닉네임">
				닉네임 : <input name="nickName" type="text"
					value="${loginedMember.nickname}" />
			</div>
			<div class="전화번호">
				휴대전화 번호 : <input name="phNum" type="tel"
					pattern="(010)-\d{3,4}-\d{4}" title="형식 010-0000-0000"
					value="${loginedMember.phNum}" />
			</div>
			<div class="이메일">
				이메일: <input name="email" type="email" value="${loginedMember.email}" />
			</div>

			<div>
				<input type="submit" value="변경" />
				<button type="button" onclick="history.back()">뒤로가기</button>
			</div>
		</form>
	</div>


	<%@ include file="../../part/foot.jspf"%>