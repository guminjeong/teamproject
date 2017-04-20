package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardContentAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardContent execute()");
		
		ActionForward forward = new ActionForward();
		// num pageNum 파라미터 가져오기
		int num = Integer.parseInt(request.getParameter("num")) ;
		String pageNum = request.getParameter("pageNum");

		//BoardDAO bdao 객체생성
		BoardDAO bdao = new BoardDAO();

		//조회수 증가 readcount 1증가 update readcount=readcount+1
		//메서드 호출 updateReadcount(num)
		bdao.updateReadcount(num);

		//BoardBean bb = 메서드 호출 getBoard(num)
		BoardBean bb = bdao.getBoard(num);
		
		//저장 bb  pageNum
		request.setAttribute("bb",bb);
		request.setAttribute("pageNum", pageNum);
		
		
		forward=new ActionForward();
		forward.setPath("./board/content.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
