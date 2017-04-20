<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>WebContent/jst1/el.jsp</h1>
<%
	// EL(Xxpression Language) : 표현언어
	// EL내장 객체 sessionScope param 
	
	session.setAttribute("sesname", "session value");
	
	// < %=  % >   대신     ${ }  사용
%>

<form action="el2.jsp" method="post">
	이름 : <input type="text" name="name"><br>
	<input type="submit" value="입력">
</form>


</body>
</html>