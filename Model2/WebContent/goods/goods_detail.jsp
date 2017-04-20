<%@page import="net.admin.goods.db.GoodsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function isBasket(){
		if(document.gfr.size.value==""){
			alert("사이즈 선택하세요");
			document.gfr.size.focus();
			return;
		}
		if(document.gfr.color.value==""){
			alert("색상 선택하세요");
			document.gfr.color.focus();
			return;
		}
		var is=confirm("장바구니에 저장하시겠습니까?");
		if(is==true){
		document.gfr.action="./BasketAdd.ba";
		document.gfr.submit();
		}else{
			return;
		}
	}
</script>
</head>
<body>
<%
//request.setAttribute("gBean", gBean);
GoodsBean goodsbean=(GoodsBean)request.getAttribute("gBean");
%>
<h1>상품상세보기</h1>
<form action="" method="post" name="gfr">
<input type="hidden" name="num" value="<%=goodsbean.getNum()%>">
<table border="1">
<tr><td><img src="./upload/<%=goodsbean.getImage().split(",")[0] %>" width="300" height="300"><br>

</td>
<td>상품이름:<%=goodsbean.getName() %> <br>
판매가격: <%=goodsbean.getPrice() %>원<br>
수량:<input type="text" name="amount" value="1"> <br>
남은수량(<%=goodsbean.getAmount() %>)개 <br>
 크기: <select name="size">
 	<option value="">크기를 선택하세요</option>
 	<c:forTokens var="size" items="${gBean.size }" delims=",">
 		<option value="${size}">${size}</option>
 	</c:forTokens>
 </select>
 <br>
색깔: <select name="color">
 	<option value="">색상를 선택하세요</option>
 	<c:forTokens var="color" items="${gBean.color }" delims=",">
 		<option value="${color}">${color}</option>
 	</c:forTokens>
 </select><br>
<a href="javascript:isBasket()">[장바구니담기]</a>
<a href="javascript:isBuy()">[구매하기]</a></td></tr>
</table>
</form>
</body>
</html>




