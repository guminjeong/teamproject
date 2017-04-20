<%@page import="net.member.db.MemberBean"%>
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
	<h1>WebContent/jsp6/insertForm.jsp</h1>
	<%
	//String id = 세션값 가져오기
	String id = (String)session.getAttribute("id");

	//세션값이 없으면 loginForm.jsp이동
	if(id==null){
		response.sendRedirect("./MemberLogin.me");
	}

	//MemberBean mb = mb데이터 가져오기
	MemberBean mb = (MemberBean)request.getAttribute("mb");
	
	// gender가 null이면 gender="남"
	// String gender = mb가져오기
	String gender = mb.getGender();
	if(gender == null){
		gender = "남";
	}
%>
	<h1>회원수정</h1>
	<form action="./MemberUpdateAction.me" method="post" name="fr">
		아이디 : <input type="text" name= "id" value="<%= id%>" readonly><br> 
		비밀번호 : <input type="password" name="pass"><br> 
		이름 : <input type="text" name= "name" value="<%=mb.getName()%>"><br> 
		나이 : <input type="text" name= "age" value="<%= mb.getAge()%>"><br>
		성별 : <input type="radio" name="gender" value="남" <%if(gender.equals("남")){%>checked<%} %>>남 <input
			type="radio" name="gender" value="여" <%if(gender.equals("여")){%>checked<%} %>>여<br> 
		이메일 : <input type="text" name= "email" value="<%= mb.getEmail()%>"><br> 
		<input type="submit" value="회원수정">
	</form>
</body>
</html>