<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="회원가입" />

<%@ include file="../../part/head.jspf"%>
	
<body>
	<h1>회원리스트</h1>
	<section class="info-list gray">
	<a href="join">${pageTitle}</a>
	<c:forEach var="member" items="${members}">
		<div>
			회원 번호 :
			${member.memberNum}
			아이디 :
			${member.loginId}
			이름 :
			${member.name}
			닉네임 :
			${member.nickname}
			가입 일자 :
			${member.regDate}
			<hr />
		</div>
	</c:forEach>
	</section>
<%@ include file="../../part/foot.jspf"%>