<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Member"%>
<%
	List<Member> members = (List<Member>)request.getAttribute("members");
%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>회원리스트</title>
</head>
<body>
	<h1>회원리스트</h1>
	<%
	for(Member member : members){
	%>
	
	<div>
		회원 번호 : <%= member.memberNum %>
		아이디 : <%= member.loginId %>
		이름 : <%= member.name %>
		닉네임 : <%= member.nickname %>
		가입 일자 : <%= member.regDate %>
		<hr />
	</div>

	<%
	}
	%>
</body>
</html>