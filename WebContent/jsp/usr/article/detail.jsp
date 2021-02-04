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
		좋아요 : ${article.like}
		<br />
		싫어요 : ${article.unLike}
		<section class="detail__article-head flex flex-jc-ar">
			<script type="text/x-template"># 제목: ${article.title}</script>
			<div class="toast-ui-viewer flex flex-ai-c"></div>
			<div class="flex flex-ai-c detail__like-unLike">
				<a href="doLike?num=${article.num}"><i class="far fa-heart"></i></a>
				<a href="doUnLike?num=${article.num}"><i class="fas fa-thumbs-down"></i></a>
			</div>
		</section>

		<section class="detail__article-body">
			<h1>내용:</h1>
			<script type="text/x-template">${article.body}</script>
			<div class="toast-ui-viewer"></div>
		</section>

		<hr />
		<a href="modify?num=${article.num}">글 수정하기</a>
		<a href="list?boardNum=${article.boardNum}">목록으로 이동</a>
		<button type="button" onclick="history.back()">뒤로가기</button>
	</div>
	<%@ include file="../../part/foot.jspf"%>