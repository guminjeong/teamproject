package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdate execute()");
		
		//세션제어
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		//MemberDAO 객체생성
		MemberDAO mdao = new MemberDAO();
		
		//MemberBean mb=메서드 호출 getMember(id)
		MemberBean mb = mdao.getMember(id);
		
		//저장 request
		request.setAttribute("mb", mb);
		
		//이동 ./member/updateForm.jsp
		forward = new ActionForward();
		forward.setPath("./member/updateForm.jsp");
		forward.setRedirect(false);
		return forward;
		
	}

}
