<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

table{
	border : 1px solid green;
}

td{
	width : 300px;
	height : 50px;
	text-align: center;
}

th{
	height : 60px;
	background : skyblue;

}

h1{
	color : red;
}


</style>


</head>
<body>
<h1>JSP : Java Server Page</h1>

서버내에서 실행되는 프로그램<br>
html코드와 java코드를 동시에 기술가능하다.<br>
java코드는 &lt;%     %>  기호 사이에 기술 한다.<br>
java실행 변수는 출력시  &lt;%= %> 사이에 기술한다.<br>

<%
	String userId = request.getParameter("id");
	String userPass = request.getParameter("pass");	
	//서버의 DB와 연결해서 table데이터를 검색
	//결과를 응답페이지로 생성한다.

%>

<table border = "1">
	<tr>
		<th>아이디</th>
		<th>비밀번호</th>	
	</tr>
	
	<tr>	
		<td><%= userId %></td>
		<td><%= userPass %></td>	
	</tr>

</table>

</body>
</html>