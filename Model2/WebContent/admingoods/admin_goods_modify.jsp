<%@page import="net.admin.goods.db.GoodsBean"%>
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
//request.setAttribute("goodsbean", goodsbean);
GoodsBean goodsbean=(GoodsBean)request.getAttribute("goodsbean");
%>
<h1>상품수정</h1>
<form action="./GoodsModifyAction.ag" method="post">
<input type="hidden" name="num" value="<%=goodsbean.getNum()%>">
<table border="1">
<tr><td>카테고리</td><td>
<select name="category">
<option value="outwear" <%if(goodsbean.getCategory().equals("outwear")){%>selected<%}%>>아웃웨어</option>
<option value="fulldress" <%if(goodsbean.getCategory().equals("fulldress")){%>selected<%}%>>정장/신사복</option>
<option value="Tshirts" <%if(goodsbean.getCategory().equals("Tshirts")){%>selected<%}%>>티셔츠</option>
<option value="shirts" <%if(goodsbean.getCategory().equals("shirts")){%>selected<%}%>>와이셔츠</option>
<option value="pants" <%if(goodsbean.getCategory().equals("pants")){%>selected<%}%>>팬츠</option>
<option value="shoes" <%if(goodsbean.getCategory().equals("shoes")){%>selected<%}%>>슈즈</option>
</select></td></tr>
<tr><td>상품이름</td><td><input type="text" name="name" value="<%=goodsbean.getName()%>"></td></tr>
<tr><td>판매가</td><td><input type="text" name="price" value="<%=goodsbean.getPrice()%>"></td></tr>
<tr><td>색깔</td><td><input type="text" name="color" value="<%=goodsbean.getColor()%>"></td></tr>
<tr><td>수량</td><td><input type="text" name="amount" value="<%=goodsbean.getAmount()%>"></td></tr>
<tr><td>사이즈</td><td><input type="text" name="size" value="<%=goodsbean.getSize()%>"></td></tr>
<tr><td>인기상품</td>
<td><input type="radio" name="best" value="1" <%if(goodsbean.getBest()==1){%>checked<%}%>>예
<input type="radio" name="best" value="0" <%if(goodsbean.getBest()==0){%>checked<%}%>>아니오</td></tr>
<tr><td>제품정보</td><td><input type="text" name="content" value="<%=goodsbean.getContent()%>"></td></tr>
<tr><td colspan="2"><input type="submit" value="상품수정">
<input type="reset" value="다시등록"></td></tr>
</table>
</form>
</body>
</html>



