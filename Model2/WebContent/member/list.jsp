
<%@page import="net.member.db.MemberBean"%>
<%@page import="net.member.db.MemberDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Statement"%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>WebContent/jsp6/list.jsp</h1>
<%
	//세션가져오기
	String id = (String)session.getAttribute("id");
	//세션없으면 또는 세션이 admin이아니면 main.jsp 이동
	if(id==null || !id.equals("admin")){
		response.sendRedirect("./Main.me");
	}
	
	List memberList = (List)request.getAttribute("memberList");
	
%>
	<table border = "1">
	<tr>
		<td>아이디</td><td>비밀번호</td>
		<td>이름</td><td>가입일자</td>
		<td>나이</td><td>성별</td><td>이메일</td>
	</tr>
	
	<%
		
		for(int i=0;i<memberList.size();i++){
			//MemberBean mb = 한칸의 데이터 가져와서 저장 .get();
			MemberBean mb = (MemberBean)memberList.get(i);
		%>
		
	<tr>
		<td><%=mb.getId() %></td><td><%=mb.getPass() %></td>
		<td><%=mb.getName() %></td><td><%= mb.getReg_date()%></td>
		<td><%= mb.getAge()%></td><td><%= mb.getGender()%></td><td><%= mb.getEmail()%></td>
	</tr>
	<%
	}
	%>
</table>
</body>
</html>