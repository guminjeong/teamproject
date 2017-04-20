<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
%>
<h1>WebContent/jst1/e2.jsp</h1>
<h2>세션값 : ${sessionScope.sesname}</h2>
<h2>파라미터값 : ${param.name} </h2>

<h3>\${5+7}=${5+7 }</h3>
<h3>\${5==7}=${5==7 }</h3>
<h3>\${5+3==8?8:10}=${5+3==8?8:10 }</h3>
</body>
</html>