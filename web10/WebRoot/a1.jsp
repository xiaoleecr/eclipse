<%@page pageEncoding="utf-8" 
contentType="text/html;charset=utf-8" %>
<html>
	<head></head>
	<body style="font-size:30px;">
		<%
			pageContext.setAttribute("name","李白");
		%>
		<%=pageContext.getAttribute("name") %>
	</body>
</html>