<%@include file="/common/taglib.jsp" %>
<%@page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>User: Home</h1>
	<c:if test="${not empty userojb }">
		<h1>Name: ${userojb.name }</h1>
		<h1>Email: ${userojb.email }</h1>
	</c:if>
</body>
</html>