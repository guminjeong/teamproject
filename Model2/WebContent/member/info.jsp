<%@page import="net.member.db.MemberBean"%>
<%@page import="net.member.db.MemberDAO"%>
<%@page import="java.sql.Timestamp"%>
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
<h1>WebContent/member/info.jsp</h1>
<%
	
	//String id = 세션값 가져오기
	String id = (String)session.getAttribute("id");

	//세션값이 없으면 loginForm.jsp이동
	if(id==null){
		response.sendRedirect("./MemberLogin.me");
	}
	
	MemberBean mb = (MemberBean)request.getAttribute("mb");
	
	//저장된 MemberBean mb 데이터 가져오기
	
%>
아이디 : <%= mb.getId() %> <br>
비밀번호 : <%= mb.getPass()%> <br>
이름 : <%= mb.getName()%> <br>
회원가입일자 : <%= mb.getReg_date()%> <br>
나이 : <%= mb.getAge()%> <br>
성별 : <%= mb.getGender()%> <br>
이메일 : <%= mb.getEmail()%> <br>

<a href="./Main.me">main으로 이동</a>
</body>
</html>