package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdateAction execute()");
		
		//세션제어
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
				
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		//한글처리
		request.setCharacterEncoding("utf-8");
		
		MemberBean mb = new MemberBean();
		
		mb.setId(request.getParameter("id"));
		mb.setPass(request.getParameter("pass"));
		mb.setName(request.getParameter("name"));
		mb.setAge(Integer.parseInt(request.getParameter("age")));
		mb.setGender(request.getParameter("gender"));
		mb.setEmail(request.getParameter("email"));
		
		MemberDAO mdao = new MemberDAO();
		int check = mdao.updateMember(mb);
		
		if(check==0){ //비밀번호 틀림
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
			
		}else if(check==-1){ //아이디 없음
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
			
		}else{
			//ActionForward는 함께쓸수없다 경고메시지를 띄우지않을시 ActionForward
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정성공');");
			out.println("location.href='./Main.me';");
			out.println("</script>");
			out.close();
			return null;		
		}
		
	}

}
