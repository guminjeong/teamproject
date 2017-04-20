package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLoginAction execute()");
		//한글처리 
		request.setCharacterEncoding("utf-8");
		
		//id pass 파라미터 가져오기
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		//MemberDAO 객체 생성 mdao
		MemberDAO mdao = new MemberDAO();
		
		//메서드호출 idCheck(id, pass)
		int check = mdao.idCheck(id, pass);
		
		 if(check==0){ //"비밀번호틀림" 뒤로이동
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 틀림');");//;이 엔터기능(문장의 끝을 알려주는 역할) 꼭넣어주어야함
			out.println("history.back();");
			out.println("</script>");
			out.close();//출력이끝남
			return null;//함수를 호출하는곳으로 되돌아감 밑에구문은 더이상 실행되지않음
			
		}else if(check==-1){ //"아이디없다" 뒤로이동
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('아이디없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();//출력이끝남
			return null;
			
		}else{ //check =1 => 로그인성공 => 로그인 인증 세션값생성 "id", id ./Main.me
				//세션도 객체를 생성하고 사용해주어야한다.
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			ActionForward forward = new ActionForward();
			forward.setPath("./Main.me");
			forward.setRedirect(true);
			return forward;
		}
		
	}

}
