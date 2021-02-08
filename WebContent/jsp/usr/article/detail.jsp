<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${article.extra__boardName}" />

<%@ include file="../../part/head.jspf"%>

<body>
	<h1>게시판 이름: ${pageTitle}</h1>
	<h2>게시물 이름</h2>
	<a onclick="if ( confirm('정말 삭제하시겠습니까?') == false) {return false; };"
		href="doDelete?num=${article.num}">글 삭제</a>
	<div>
		번호 : ${article.num}
		<br />
		작성날짜 : ${article.regDate}
		<br />
		갱신날짜 : ${article.updateDate}
		<br />
		작성자 : ${article.extra__writer}
		<br />
		좋아요 : ${article.extra__likeOnlyPoint}
		<br />
		싫어요 : ${article.extra__disLikeOnlyPoint}

		<section class="detail__article-head flex flex-jc-ar">
			<script type="text/x-template"># 제목: ${article.title}</script>
			<div class="toast-ui-viewer flex flex-ai-c"></div>
			<div class="flex flex-ai-c detail__like-unLike">

				<c:if test="${article.extra.actorCanLike}">
					<a class="btn btn-primary"
						href="../like/doLike?relTypeCode=article&relId=${article.num}&redirectUrl=${encodedCurrentUrl}"
						onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;">
						<span>
							<i class="far fa-heart"></i>
						</span>
						<span>좋아요</span>
					</a>

					<a class="btn btn-danger"
						href="../like/doDislike?relTypeCode=article&relId=${article.num}&redirectUrl=${encodedCurrentUrl}"
						onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;">
						<span>
							<i class="fas fa-thumbs-down"></i>
						</span>
						<span>싫어요</span>
					</a>
				</c:if>

				<c:if test="${article.extra.actorCanCancelLike}">
					<a class="btn btn-info"
						href="../like/doCancelLike?relTypeCode=article&relId=${article.num}&redirectUrl=${encodedCurrentUrl}"
						onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
						<span>
							<i class="fas fa-heart"></i>
						</span>
						<span>좋아요 취소</span>
					</a>
				</c:if>

				<c:if test="${article.extra.actorCanCancelDislike}">
					<a class="btn btn-info"
						href="../like/doCancelDislike?relTypeCode=article&relId=${article.num}&redirectUrl=${encodedCurrentUrl}"
						onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
						<span>
							<span>
								<i class="fas fa-slash"></i>
							</span>
						</span>
						<span>싫어요 취소</span>
					</a>
				</c:if>
			</div>
		</section>

		<section class="detail__article-body">
			<h1>내용:</h1>
			<script type="text/x-template">${article.body}</script>
			<div class="toast-ui-viewer"></div>
		</section>
		<hr />
		
		<c:if test="${isLogined}">
			<section class="detail__reply_write_box">
			
			<script>
			let DoWriteReplyForm__submited = false;
			let DoWriteReplyForm__checkedLoingId = "";

			function DoWriteReplyForm_submit(form) {
				if (DoWriteReplyForm__submited) {
					alert('처리중입니다.');
					return;
				}
				const editor = $(form).find('.toast-ui-editor').data('data-toast-editor');
				const body = editor.getMarkdown().trim();

				if (body.length == 0) {
					alert('내용을 입력해주세요.12332');
					editor.focus();

					return;
				}

				form.body.value = body;

				form.submit();
				DoWriteReplyForm__submited = true;
			}
		</script>
				<form class="con" action="../reply/doWriteReply" method="POST"
					onsubmit="DoWriteReplyForm_submit(this); return false;">
					<input type="hidden" name="redirectUrl" value="${currentUrl}">
					<input type="hidden" name="relTypeCode" value="article">
					<input type="hidden" name="relId" value="${article.num}">
					<input type="hidden" name="body">

					<div class="detail__reply_body">
						<script type="text/x-template"></script>
						<div class="toast-ui-editor" data-height=200></div>
					</div>

					<div class="button_write">
						<input class="detail__write" type="submit" value="작성" />
					</div>
				</form>
		</c:if>
		</section>
		<a href="modify?num=${article.num}">글 수정하기</a>
		<a href="list?boardNum=${article.boardNum}">목록으로 이동</a>
		<button type="button" onclick="history.back()">뒤로가기</button>
	</div>
	<%@ include file="../../part/foot.jspf"%>