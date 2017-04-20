<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>WebContent/jsp6/main.jsp</h1>
<%
	//String id = 세션값 가져오기  //다운캐스팅 형변환
	String id = (String)session.getAttribute("id"); //원래 세션은 모든형으로저장가능(object)

	//세션값이없으면(id 변수 값이 null이면)
	if(id == null){
		//이동 loginForm.jsp
		response.sendRedirect("./MemberLogin.me");
	}

%>

<%=id %>님이 로그인 하셨습니다
<input type="button" value="로그아웃" onclick="location.href='./MemberLogoutAction.me'"><br>
<a href="./MemberInfo.me">회원정보조회</a><br>
<a href="./MemberUpdate.me">회원정보수정</a><br>
<a href="./MemberDelete.me">회원정보삭제</a><br>

<% 
	//%를 전체로 실행을 시켜서 충돌이 일어나게된다 위의 id값 제어랑
	//id 가  "admin" 이면 회원목록 하이퍼링크 보이게함
	if(id != null){
		if(id.equals("admin")){
%>
<a href="./MemberList.me">회원목록</a><br>
<%} 
}%>
</body>
</html>