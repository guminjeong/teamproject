<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//JSTL(JSP Stnadard Tag Libray)
	//자바코드를 최대한 줄이기 위해 제공된 태그
	//자주사용되는 필요한 기능응 모아옿은 커스텀태그 
	//코드를 간결하게 해주고.가독성을 높이는 장점
	//http://jakarta.apache.org
	
	//core fmt xml sql
%>
<h1>WebContent/jst1/jstl_core.jsp</h1>
<c:set var="test" value="JSTL TEST"/>
변수선언 후 : <c:out value="${test}" /><br>
<c:remove var="test"/>
변수값삭제 후 : <c:out value="${test}" /><br>

<c:catch var="err">
<%=10/0 %><br>
</c:catch>

catch잡은 오류 : <c:out value="${err}"/><br>

<c:if test="${5<10 }">
5는 10보다 작다<br>
</c:if>

<c:if test="${5>10 }">
5는 10보다 크다<br>
</c:if>

<c:choose>
	<c:when test="${5+10==50 }">
		5+10은 50이다.<br>
	</c:when>
	
	<c:otherwise>
		5+10은 50이 아니다.<br>
	</c:otherwise>
</c:choose>
</body>
</html>