package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberInfo implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberInfo execute()");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		
		//없으면 ./MemberLogin.me 	ActionForward 이용 이동
		if(id==null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		//MemberDAO mdao 객체 생성
		MemberDAO mdao = new MemberDAO();
		//MemberBean mb = 메서드 호출 getMember(id)
		MemberBean mb = mdao.getMember(id);
		
		//데이터 저장 mb
		request.setAttribute("mb", mb);
		
		//이동 ./member/info.jsp
		forward = new ActionForward();
		forward.setPath("./member/info.jsp");
		forward.setRedirect(false);
		return forward;
		
		//회원정보 수정
		// /MemberUpdate.me =>MemberUpdate 자바파일 =>updateForm.jsp로이동
		// ./member/updateForm.jsp 이동
		
	}
	
}
