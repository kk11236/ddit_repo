<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

table{
	border : 1px solid blue;
	margin : 50px;
}
td{
	width : 300px;
	height : 50px;
	text-align: center;	
}
.title{
	background: lightblue;
}


</style>

</head>
<body>

	<h1>JSP : Java Server Page</h1>

	<%	
	request.setCharacterEncoding("UTF-8");
	
	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	String userTel = request.getParameter("tel");
	String userAddr = request.getParameter("addr");
	String userAge = request.getParameter("age");
	String userFile = request.getParameter("file");
	//DB crud처리 결과를 얻는서
	//응답 데이터를 생선한다
	%>

	<!-- id는 하나만 유일하게 올 수 있음  #-->
	<!-- class는 똑같은 이름으로 구분해서 설정할때  . -->
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
			<td class="title">전화번호</td>
			<td><%=userTel%></td>
		</tr>

		<tr>
			<td class="title">주소</td>
			<td><%=userAddr%></td>
		</tr>

		<tr>
			<td class="title">나이</td>
			<td><%=userAge%></td>
		</tr>
		
		<tr>
			<td class="title">첨부파일</td>
			<td><%=userFile%></td>
		</tr>

	</table>

</body>
</html>