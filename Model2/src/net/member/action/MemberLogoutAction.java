package net.member.action;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLogout execute()");
		
		//세션 초기화
		HttpSession session = request.getSession();
		session.invalidate();
		
		//로그아웃
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('로그아웃')");
		out.println("location.href='./Main.me'");
		out.println("</script>");
		out.close();
		return null;
		
		//./Main.me 이동
	
	}
	
}
