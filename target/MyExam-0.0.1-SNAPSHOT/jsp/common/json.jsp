<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="com.sbs.example.jspCommunity.Util.Util"%>
<%
Object data = request.getAttribute("data");
response.getWriter().print(Util.getJsonText(data));
%>