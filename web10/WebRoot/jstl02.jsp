<%@page pageEncoding="utf-8" 
contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>
<html>
	<head></head>
	<body style="font-size:30px;">
		<%
			User user = new User();
			user.setAge(22);
		    user.setGender("x");
		    request.setAttribute("user",user);
		%>
		性别:<c:choose>
			<c:when test="${user.gender == 'm'}">男</c:when>
			<c:when test="${user.gender == 'f'}">女</c:when>
			<c:otherwise>保密</c:otherwise>
		</c:choose><br/>
		年龄:<c:choose>
			<c:when test="${user.age < 18}">未成年</c:when>
			<c:when test="${user.age >= 18 && user.age < 60}">成年</c:when>
			<c:otherwise>老年</c:otherwise>
		</c:choose>
		
	</body>
</html>