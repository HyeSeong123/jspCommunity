<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${article.title}" />

<%@ include file="../../part/head.jspf"%>
<h2>${pageTitle}))게시물수정</h2>

<div>
	<span>원본 내용</span>
	<div>제목: ${article.title}</div>
	<div>내용: ${article.body}</div>
</div>
<br>

<div>
	<script>
		function DoModifyForm__submit(form) {
			let DoModifyForm_submited = false;

			if (DoModifyForm_submited) {
				alert('처리중입니다.');
				return;
			}

			form.title.value = form.title.value.trim();

			if (form.title.value.length == 0) {
				alert('제목을 입력해주세요.');
				form.title.focus();
				return;
			}

			form.body.value = form.body.value.trim();

			const editor = $(form).find('.toast-ui-editor').data('data-toast-editor');
			const body = editor.getMarkdown().trim();
			
			if (body.length == 0) {
				alert('내용을 입력해주세요.');
				form.body.focus();
				return;
			}
			form.body.value = body;
			
			form.submit();
			DoModifyForm_submited = true;
		}
	</script>
	<span>수정</span> <br> <br>
	<form action="doModify" method="POST" onsubmit="DoModifyForm__submit(this); return false;">
		<input type="hidden" name="memberNum" value="${sessionScope.loginedMemberId}">
		<input type="hidden" name="num" value="${article.num}" />	
		<input type="hidden" name="body" />
		
		<input	type="text" name="title" placeholder="수정할 제목을 입력해주세요">
		<hr>
		<div class="modify_body">
				<script type="text/x-template">${article.body}</script>
				<div class="toast-ui-editor"></div>
			</div>
		<hr>
		<input type="submit" value="변경">
		<button type="button" onclick="history.back()">뒤로가기</button>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>