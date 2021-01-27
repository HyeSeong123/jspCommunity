<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="회원가입" />

<%@ include file="../../part/head.jspf"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<body>
	<h1>${pageTitle}</h1>
	<div>
		<script>
		let doJoinForm_submited = false;
		let doJoinForm_checkedLoginId = "";
		
		function DoJoinForm__checkLoginDup(el){
			const from = $(el).closest('form').get(0);
			const loginId = from.loginId.value;

			
			$.get(
				"getLoginIdDup",
				{
					loginId
				},
				function(data){
					if(data.msg){
						alert(data.msg);
					}
					if(data.success){
						doJoinForm_checkedLoginId = data.body.loginId;
					}
				},	
				"json"
			);
		}
			function DoJoinForm__submit(form) {

				if (doJoinForm_submited) {
					alert('처리중입니다.');
					return;
				}

				form.loginId.value = form.loginId.value.trim();

				if (form.loginId.value.length == 0) {
					alert('로그인 아이디를 입력해주세요.');
					form.loginId.focus();
					return;
				} else if (form.loginId.value.length <= 4) {
					alert('아이디는 5글자 이상이어야 합니다.');
					form.loginId.focus();
					return;
				}

				if (form.loginId.value != doJoinForm_checkedLoginId) {
					alert('아이디 중복검사를 해주세요.');
					btnLoginIdDupCheck.focus();
					return false;
				}
				
				form.loginPw.value = form.loginPw.value.trim();

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
				form.name.value = form.name.value.trim();
				
				if (form.name.value.length == 0) {
					alert('이름을 입력해주세요.');
					form.name.focus();

					return;
				}
				form.nickname.value = form.nickname.value.trim();

				if (form.nickname.value.length == 0) {
					alert('닉네임을 입력해주세요.');
					form.nickname.focus();
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
				form.loginPwReal.value = sha256(form.loginPw.value);
				form.loginPw.value = "";
				form.confirmPw.value = "";
				
				form.submit();
				doJoinForm_submited = true;
			}
		</script>

		<form action="doJoin" method="POST"
			onsubmit="DoJoinForm__submit(this); return false;">
			<input type="hidden" name="loginPwReal" />

			<div>아이디 :</div>
			<div>
				<input type="text" name="loginId" />
				<button onclick="DoJoinForm__checkLoginDup(this);"
					name="btnLoginIdDupCheck" type="button">중복 체크</button>
			</div>

			<div>패스워드 :</div>
			<div>
				<input name="loginPw" type="password" />
			</div>

			<div>패스워드 확인 :</div>
			<div>
				<input name="confirmPw" type="password" />
			</div>

			<div>이 름 :</div>
			<div>
				<input type="text" name="name" />
			</div>

			<div>닉네임 :</div>
			<div>
				<input type="text" name="nickname" />
			</div>
			<div>이메일 :</div>
			<div>
				<input type="email" name="email" />
			</div>
			<div>휴대전화 번호 :</div>
			<div>
				<input type="tel" name="phNum" />
			</div>
			<hr />
			<div>
				<input type="submit" value="가입" />
				<button type="button" onclick="history.back()">뒤로가기</button>
			</div>
		</form>
	</div>

	<%@ include file="../../part/foot.jspf"%>