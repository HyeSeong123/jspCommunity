<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.Dto.Article"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<script>
var alertMsg = '<%=request.getAttribute("alertMsg")%>'.trim();
if(alertMsg){
	alert(alertMsg);
}
var historyBack = '<%=request.getAttribute("historyBack")%>' == 'true';
if(historyBack){
	history.back();
}
var replaceUrl = '<%=request.getAttribute("replaceUrl")%>'.trim();

	if (replaceUrl) {
		location.replace(replaceUrl);
	}
</script>