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
				
				<script>
					// 좋아요
					function article__updateLikePoint(newLikePoint){
						$('.like-point').empty().append(newLikePoint);
					}
					const num = ${article.num};
					
					function DoLikeForm() {
						$.post(
							"../like/getLikeCount",
							{
								"relTypeCode" : "article",
								num
							},
							function(data){
								if(data.resultCode.substr(0,2) == 'F-'){
									alert(data.msg);
								} 
								if(data.resultCode.substr(0,2) == 'S-'){
									article__updateLikePoint(data.body);
								}
							},
							"json"
						);
					}
					function DoCancleLikeForm() {
						$.post(
							"../like/getCancleLikeCount",
							{
								"relTypeCode" : "article",
								num
							},
							function(data){
								if(data.resultCode.substr(0,2) == 'F-'){
									alert(data.msg);
								} 
								if(data.resultCode.substr(0,2) == 'S-'){
									article__updateLikePoint(data.body);
								}
							},
							"json"
						);
					}

				</script>
					
					<c:if test="${isLogined}">
						<a href="#" class="btn btn-like" onclick="DoLikeForm(this); return false;">
							<i class="fas fa-heart"></i>
						</a>	
				
						<span class="like-point"> ${article.extra__likeOnlyPoint} </span>
						
						<a class="btn btn-like like_cancle" href="#" onclick="DoCancleLikeForm(this); return false;">
							<i class="far fa-heart"></i>
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
					<form class="con detail__replyBox" action="../reply/doWriteReply" method="POST"
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
			
	
			<section class="detail__reply_cover con-min-width">
				<div class="con">
					<div class="detail__reply_list_box">
					<c:forEach items="${replies}" var = "reply">
							<div class="detail__reply_list">
							<span class="reply_list_in reply_list__writer">${reply.extra__writer}</span>
							<span class="reply_list_in reply_list__body">${reply.body}</span>
							<span class="reply_list_in reply_list__updateDate">${reply.updateDate}</span>
							</div>
					</c:forEach>
					</div>
				</div>
			</section>
		</section>
	
		<div>
			<a href="list?boardNum=${article.boardNum}">목록</a>
			<button type="button" onclick="history.back()">뒤로가기</button>
		</div>
	</div>
	<%@ include file="../../part/foot.jspf"%>