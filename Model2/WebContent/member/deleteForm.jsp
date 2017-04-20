<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/member/deleteForm.jsp</h1>
	<%
		// 세션값 가져오기
		String id = (String)session.getAttribute("id");
		
		// 세션값이 없으면 loginForm.jsp 이동
		if(id==null){
			response.sendRedirect("./MemberLogin.me");
		}	
	%>
	<h1>회원 삭제</h1>
	<form action="./MemberDeleteAction.me" method="post" name="fr">
		아이디 : <input type = "text" name="id" value=<%=id %> readonly><br>
		비밀번호 : <input type = "text" name="pass" ><br>
		<input type="submit" value="회원삭제">
	</form>
</body>
</html>