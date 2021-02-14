<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${article.extra__boardName}" />

<%@ include file="../../part/head.jspf"%>

<body>
	
	<div>
		
		<section class="detail__article-head flex flex-jc-ar">
			<script type="text/x-template"># 제목: ${article.title}</script>
			<div class="toast-ui-viewer flex flex-ai-c"></div>
			<div class="flex flex-ai-c detail__like-unLike">
			
				<c:if test="${isLogined == false}">
					<span class="btn-like" title="로그인 후 클릭할 수 있습니다."> <i class="far fa-heart"></i> ${article.extra__likeOnlyPoint} </span>
					<span class="btn-unlike" title="로그인 후 클릭할 수 있습니다."> <i class="fas fa-thumbs-down"></i> ${article.extra__disLikeOnlyPoint} </span>
				</c:if>
			
				<c:if test="${article.extra.actorCanLike}">
					<a class="btn btn-like"
						href="../like/doLike?relTypeCode=article&relId=${article.num}&redirectUrl=${encodedCurrentUrl}"
						onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;">
						<span> <i class="far fa-heart"></i> </span>
						<span> ${article.extra__likeOnlyPoint} </span>
					</a>

					<a class="btn btn-unlike"
						href="../like/doDislike?relTypeCode=article&relId=${article.num}&redirectUrl=${encodedCurrentUrl}"
						onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;"> <span>
							<i class="fas fa-thumbs-down"></i>
					</span> <span> ${article.extra__disLikeOnlyPoint} </span>
					</a>
				</c:if>

				<c:if test="${article.extra.actorCanCancelLike}">
					<a class="btn btn-info"
						href="../like/doCancelLike?relTypeCode=article&relId=${article.num}&redirectUrl=${encodedCurrentUrl}"
						onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
						<span> <i class="fas fa-heart"></i>
					</span> <span>좋아요 취소</span>
					</a>
				</c:if>

				<c:if test="${article.extra.actorCanCancelDislike}">
					<a class="btn btn-info"
						href="../like/doCancelDislike?relTypeCode=article&relId=${article.num}&redirectUrl=${encodedCurrentUrl}"
						onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
						<span> <span> <i class="fas fa-slash"></i>
						</span>
					</span> <span>싫어요 취소</span>
					</a>
				</c:if>
			</div>
			
			<div class="detail__article__button flex flex-column flex-jc-c">
				<a class="del_button button_type1" onclick="if ( confirm('정말 삭제하시겠습니까?') == false) {return false; };"
				href="doDelete?num=${article.num}">글 삭제</a>
				<a class="modify_button button_type1" href="modify?num=${article.num}">글 수정</a>
			</div> 
		</section>
		
		<div class="detail__top1 flex flex-jc-ar">
			<div> 작성자 : ${article.extra__writer} </div>
			<div>작성일 : ${article.regDate}</div>
			<c:if test="${article.regDate != article.updateDate}">
				<div> 수정일 : ${article.updateDate} </div>
			</c:if>
		</div>
		
			
		
		<br />

		<section class="detail__article-body">
			<script type="text/x-template">${article.body}</script>
			<div class="toast-ui-viewer"></div>
		</section>
		
		<section class="detail__reply">
			<h2 class="detail__replysize"> 댓글 </h2> <span> (${replies.size()}) </span>
			<c:if test="${isLogined == false}">
				<span>로그인 후 이용해주세요.</span>
			</c:if>
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
							
							form.body.value = form.body.value.trim();
							
							if (body.length == 0) {
								alert('내용을 입력해주세요.');
								editor.focus();
	
								return;
							}
	
							form.submit();
							DoWriteReplyForm__submited = true;
						}
					</script>
					<form class="con" action="../reply/doWriteReply" method="POST"
						onsubmit="DoWriteReplyForm_submit(this); return false;">
						<input type="hidden" name="redirectUrl" value="${currentUrl}">
						<input type="hidden" name="relTypeCode" value="article">
						<input type="hidden" name="relId" value="${article.num}">
						<div class="detail__reply-form flex">
							<input type="text" name="body" class="detail__reply-text" placeholder="고운말을 써주세요!">
							<div class="button_write">
								<input class="detail__write" type="submit" value="작성" />
							</div>
						</div>
						
					</form>
			</c:if>
			
	
			<section class="detail__reply_list con-min-width">
				<div class="con">
					
					<c:forEach items="${replies}" var = "reply">
						<span>${reply.regDate}</span>
						<span>${reply.extra__writer}</span>
						<c:if test="${article.extra.actorCanLike}">
							<span>${reply.extra__likeOnlyPoint}</span>
							<span>${reply.extra__dislikeOnlyPoint}</span>
						</c:if>
						<div class="detail__reply_body-list">
							<script type="text/x-template">${reply.body}</script>
							<div class="toast-ui-viewer"></div>
						</div>
					</c:forEach>
				</div>
			</section>
		</section>
	</section>
		<a href="list?boardNum=${article.boardNum}">목록</a>
		<button type="button" onclick="history.back()">뒤로가기</button>
	</div>
	<%@ include file="../../part/foot.jspf"%>