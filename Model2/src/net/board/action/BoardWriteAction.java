package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("BoardWriteAction execute()");
		
		//한글처리
		request.setCharacterEncoding("utf-8");
		
		//BoardBean bb 객체생성
		BoardBean bb = new BoardBean();
		
		//자바빈 멤버변수 <= 파라미터 저장
		bb.setName(request.getParameter("name"));
		bb.setPass(request.getParameter("pass"));
		bb.setSubject(request.getParameter("subject"));
		bb.setContent(request.getParameter("content"));
		bb.setIp(request.getRemoteAddr());
		
		//BoardDAO bdao 객체생성
		BoardDAO bdao = new BoardDAO();
		
		//메서드 호출 insertBoard(bb)
		bdao.insertBoard(bb);
		
		//이동 ./BoardList.bo
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
	
		return forward;
	}

}
