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

#info{
	height : 100px;

}

</style>


</head>
<body>
<h1>JSP : Java Server Page</h1>


<%
	request.setCharacterEncoding("UTF-8");

	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	
	String userInfo = request.getParameter("area");
	
	//엔터기호 \r\n 를 <br>태그로 변경 - replaceAll('기존' , '새로운')
	userInfo = userInfo.replaceAll("\n", "<br>");
	
	//db접속 - crud처리
	
	//db처리한 결과값을 얻는다
	//결과값으로 출력
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
		<td class="title" id="info">소개</td>
		<td><%=userInfo%></td>
	
	</tr>
	
	
	</table>
	


</body>
</html>