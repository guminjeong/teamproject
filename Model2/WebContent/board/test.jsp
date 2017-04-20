<%@page import="net.board.db.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">


</style>
</head>
<body>
<%
	BoardBean bb = new BoardBean();
	if(request.getAttribute("bb")!=null){
	bb = (BoardBean)request.getAttribute("bb");
	}
%>

<table border="1" style="width: 300px;">
	<tr><th>1</th><td colspan="3"><%=bb.getName() %></td></tr>
	<tr><th>2</th><td><a href="./TestAction.bo?name=1">1</a></td><td>2</td><td>3</td></tr>
	<tr><th>3</th><td>4</td><td>5</td><td>6</td></tr>
	<tr><th>4</th><td>7</td><td>8</td><td>9</td></tr>
	<tr><th>5</th><td>10</td><td>11</td><td>12</td></tr>
</table>
</body>
</html>