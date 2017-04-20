<%@page import="net.admin.goods.db.GoodsBean"%>
<%@page import="java.util.List"%>
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
// request.setAttribute("goodsList", goodsList);
List goodsList=(List)request.getAttribute("goodsList");
%>
<h1>admin_goods_list.jsp</h1>
<h1>상품목록</h1>
<h3><a href="./GoodsAdd.ag">상품등록</a></h3>
<table border="1">
<tr><td>번호</td><td>카테고리</td><td>사진</td><td>상품명</td>
   <td>단가</td><td>수량</td><td>등록일자</td><td>수정/삭제</td></tr>
   <%
   for(int i=0;i<goodsList.size();i++){
	   GoodsBean gBean=(GoodsBean)goodsList.get(i);
	   %>
<tr><td><%=gBean.getNum() %></td><td><%=gBean.getCategory() %></td>
<td><img src="./upload/<%=gBean.getImage().split(",")[0] %>" width="50" height="50"></td><td><%=gBean.getName() %></td><td><%=gBean.getPrice() %></td>
<td><%=gBean.getAmount() %></td><td><%=gBean.getDate() %></td>
<td><a href="./GoodsModify.ag?num=<%=gBean.getNum()%>">수정</a>
    /<a href="./GoodsDelete.ag?num=<%=gBean.getNum()%>">삭제</a></td></tr>	   
	   <%
   }
   %>
   
</table>
</body>
</html>

