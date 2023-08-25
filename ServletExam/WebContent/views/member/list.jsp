<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%-- <% 여기에 자바코드 쓸수 있음%> --%>

<%

	List<MemberVO> memList = (List<MemberVO>)request.getAttribute("memList");

	String msg = (String) session.getAttribute("msg");
	if(msg != null){
		session.removeAttribute("msg");
	}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>주소</th>
		</tr>
		
<%
	if(memList.size() == 0){
		
	
%>	
	<tr><td colspan="4">데이터가 존재하지 않습니다.</td></tr>
<%
	}else{
		for(MemberVO mv : memList){
%>	
		<tr>
			<td><%=mv.getMemId() %></td>
			<td><%=mv.getMemName() %></td>
			<td><%=mv.getMemTel() %></td>
			<td><%=mv.getMemAddr() %></td>
		</tr>
<%
		}
	}
%>		

	<tr align="center">
		<td colspan="4"><a href="./insert.do">[회원 등록]</a></td>
	</tr>

	</table>
<%
	if(msg != null && msg.equals("성공")){
%>
<script>
	alert('정상적으로 처리되었습니다.');
</script>
<%
	}
%>

</body>
</html>