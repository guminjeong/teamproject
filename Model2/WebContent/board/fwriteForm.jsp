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
	//파일업로드(API)
	//업로드 프로그램 설치 WebContent - WEB-INF - cos.jar
	//www.servlets.com 다운
	//com.oreilly.servlet 왼쪽메뉴  cos-26Dec2008.zip 
	
	//1.특정폴더(upload)에 파일을 넣고 디비에는 파일이름 저장 ->mysql은 용량이 작아 이방법을쓴다.
	
	//2.파일을 => 디비저장 ->대용량의 db사용
	
	//폼태그 시작부분 enctype="multipart/form-data" 
	// => 꼭넣어주어야 String파일로 인식하지않고 multipart형태로 데이터가 넘어가게 된다.
%>
<h1>WebContent/board/fwriteForm.jsp</h1>
<h1> 게시판(자료실) 글쓰기 </h1>
<form action="./BoardFWriteAction.bo" method="post" name="fr" 
	  enctype="multipart/form-data"><!-- enctype 파일첨부가있을때 꼭 넣어줘야함 -->
	글쓴이 : <input type="text" name="name"><br>
	비밀번호 : <input type="password" name="pass"><br>
	제목 : <input type="text" name="subject"><br>
	파일첨부 : <input type="file" name="file"><br>
	내용 : <textarea rows="10" cols="20" name="content"></textarea><br>
	<input type="submit" value="글쓰기">
</form> 

</body>
</html>