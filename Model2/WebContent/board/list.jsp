
<%@page import="net.board.db.BoardBean"%>
<%@page import="net.board.db.BoardDAO"%>
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
		List boardList = (List)request.getAttribute("boardList");
		String pageNum = (String)request.getAttribute("pageNum");
		int pageCount =((Integer)request.getAttribute("pageCount")).intValue();
		int pageBlock = ((Integer)request.getAttribute("pageBlock")).intValue();
		int count = ((Integer)request.getAttribute("count")).intValue();
		int startPage = ((Integer)request.getAttribute("startPage")).intValue();
		int endPage =((Integer)request.getAttribute("endPage")).intValue();
		
		//디비객체 생성 BoardDAO bdao
		BoardDAO bdao = new BoardDAO();

		
	%>
	<h1>WebContent/board/list.jsp</h1>
	<h1>
		게시판 글목록 [전체 글의 개수
		<%=count%>]
	</h1>
	<h3>
		<a href="./BoardWrite.bo">글쓰기</a>
	</h3>
	
	<h3>
		<a href="./BoardFWrite.bo">파일업로드</a>
	</h3>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>날짜</td>
			<td>조회수</td>
			<td>IP</td>
		</tr>
		<%
			for (int i = 0; i < boardList.size(); i++) {
				BoardBean bb = (BoardBean) boardList.get(i);
		%>
		<tr>
			<td><%=bb.getNum()%></td>
			<td>
			<%
				//답글 들여쓰기 모양
				int wid=0;
				if(bb.getRe_lev()>0){
					wid=10*bb.getRe_lev();
				%>
				<img src="./board/level.gif" width="<%=wid%>" height="15px">
				<img src="./board/re.gif">
				<%				
				}
				
			%>
			<a
				href="./BoardContent.bo?num=<%=bb.getNum()%>&pageNum=<%=pageNum%>"><%=bb.getSubject()%></a></td>
			<td><%=bb.getName()%></td>
			<td><%=bb.getDate()%></td>
			<td><%=bb.getReadcount()%></td>
			<td><%=bb.getIp()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		if(count!=0){
			//이전
			if (startPage > pageBlock) {
	%><a href="./BoardList.bo?pageNum=<%=startPage - pageBlock%>">[이전]</a>
	<%
		}
			//1...10
			for (int i = startPage; i <= endPage; i++) {
	%><a href="./BoardList.bo?pageNum=<%=i%>">[<%=i%>]
	</a>
	<%
		}
			//다음
			if (endPage < pageCount) {
	%><a href="./BoardList.bo?pageNum=<%=startPage + pageBlock%>">[다음]</a>
	<%
		}

		}
	%>
	<!-- 	  1  2  3 .... 10 [다음]
	[이전] 11 12 13 .... 20 [다음]
	[이전] 21 22 23 .... 27	  -->

</body>
</html>