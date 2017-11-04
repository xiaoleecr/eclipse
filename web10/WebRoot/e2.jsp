<%@page pageEncoding="utf-8" 
contentType="text/html;charset=utf-8" 
import="java.util.*"%>
<html>
	<head></head>
	<body style="font-size:30px;">
		${1 + 1}<br/>
		${"2" + "2"}
		<br/>
		
		${1 < 2 }<br/>
		<%
			request.setAttribute("str1","abc");
		%>
		${str1 == "abc" }<br/>
		${sessionScope.str1 == "abc"}
		<br/>
		${1 < 2 && 3 > 2 }<br/>
		<%
			List list = new ArrayList();
			list.add("a");
			request.setAttribute("list1",list);
		%>
		${empty list1}<br/>
		<%
			request.setAttribute("str2","");
		%>
		${empty str2}<br/>
		${empty null}<br/>
		${empty abc}
		
		
		
	</body>
</html>