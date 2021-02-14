<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="${board.name}" />

<%@ include file="../../part/head.jspf"%>
<body>
	<h2>${pageTitle}게시물추가</h2>

	<div>
	
		<script>
			let DoWriteForm__submited = false;
			let DoWriteForm__checkedLoingId = "";

			function DoWriteForm_submit(form) {
				if (DoWriteForm__submited) {
					alert('처리중입니다.');
					return;
				}
				form.title.value = form.title.value.trim();

				if (form.title.value.length == 0) {
					alert('제목을 입력해주세요.');
					form.title.focus();

					return;
				}
				
				const editor = $(form).find('.toast-ui-editor').data('data-toast-editor');
				const body = editor.getMarkdown().trim();

				if (body.length == 0) {
					alert('내용을 입력해주세요.');
					editor.focus();

					return;
				}

				form.body.value = body;

				form.submit();
				DoWriteForm__submited = true;
			}
		</script>
		
		<form action="doWrite" method="POST" onsubmit="DoWriteForm_submit(this); return false;">
			<input type="hidden" name="loginedMemberNum" value="${loginedMemberNum}">
			<input type="hidden" name="boardNum" value="${board.boardNum}">
			<input type="hidden" name="body" />
			<input type="text" name="title" placeholder="제목을 입력해주세요">
			<hr />
			<div class="write_body">
				<script type="text/x-template">${article.body}</script>
				<div class="toast-ui-editor"></div>
			</div>
			<hr />
			<input type="submit" value="add">
			<button type="button" onclick="history.back()">뒤로가기</button>
		</form>
	</div>
	<%@ include file="../../part/foot.jspf"%>