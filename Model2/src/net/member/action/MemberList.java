package net.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberList execute()");
		//세션제어
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		
		if(id == null || !id.equals("admin")){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		MemberDAO mdao = new MemberDAO();
		
		List memberList = mdao.getMemberList();
		
		request.setAttribute("memberList", memberList);
		
		forward = new ActionForward();
		forward.setPath("./member/list.jsp");
		forward.setRedirect(false);
		return forward;
	}
	

}
