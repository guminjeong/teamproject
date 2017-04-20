package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardUpdateAction execute()");
		//한글처리
		request.setCharacterEncoding("utf-8");
		
		//pageNum 파라미터 가져오기
		String pageNum = request.getParameter("pageNum");
		
		//BoardBean bb 객체생성
		BoardBean bb = new BoardBean();
		
		//setNum() 메서드 호출 <= num 파라미터
		//name pass subject content 자바빈 저장
		bb.setNum(Integer.parseInt(request.getParameter("num")));
		bb.setName(request.getParameter("name"));
		bb.setPass(request.getParameter("pass"));
		bb.setSubject(request.getParameter("subject"));
		bb.setContent(request.getParameter("content"));
		
		//BoardDAO bdao 객체생성
		BoardDAO bdao = new BoardDAO();
		
		//int check=메서드 호출 updateBoard(bb)
		int check=bdao.updateBoard(bb);
		request.setAttribute("pageNum", pageNum);
		
		if(check==0){ //'비밀번호 틀림' 뒤로이동
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
			
		}else if(check==-1){ //'게시판 글 번호 없음' 뒤로이동
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시판 번호 없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
			
		}else{ //'수정성공' 이동 ./BoardList.bo?pageNum
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정성공');");
			out.println("location.href='./BoardList.bo?pageNum="+pageNum+"';");
			out.println("</script>");	
			out.close();
			return null;
		}
		
	}

}
