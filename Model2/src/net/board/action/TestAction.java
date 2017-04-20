package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;

public class TestAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ddd");
		
		ActionForward forward = null;
		
		BoardBean bb = new BoardBean();
		bb.setName(request.getParameter("name"));
		
		request.setAttribute("bb", bb);
		forward = new ActionForward();
		forward.setPath("./board/test.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
