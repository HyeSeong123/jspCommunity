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
		회원 번호 : <%=member.getMemberNum()%>
		아이디 : <%= member.getLoginId() %>
		이름 : <%= member.getName() %>
		닉네임 : <%= member.getNickname() %>
		가입 일자 : <%= member.getRegDate() %>
		<hr />
	</div>

	<%
	}
	%>
</body>
</html>