<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
table{
	border : 2px solid green;
}

td{
	width : 300px;
	height :50px;
	text-align: center;
}
.title{
	background: lightgreen;
}


</style>

</head>
<body>

<%
	request.setCharacterEncoding("UTF-8");

	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	String userCar = request.getParameter("car");
	
	String cars[] = request.getParameterValues("cars");
	
	String str = "";
	
	for(String car : cars){		
		str += car + "<br>";		
	}
			
%>

	<table border="1">
	
	<tr>
		<td class="title">아이디</td>
		<td><%=userId%></td>
	
	</tr>
	
	<tr>
		<td class="title">이름</td>
		<td><%=userName%></td>
	
	</tr>
	
	<tr>
		<td class="title">자동차</td>
		<td><%=userCar%></td>
	
	</tr>
	
	<tr>
		<td class="title">자동차들</td>
		<td><%= str %></td>
	
	</tr>
		
	</table>

</body>
</html>