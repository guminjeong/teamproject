package net.member.action;

import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberJoinAction implements Action{
	/*alt shift s=> v*/
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberjoinAction execute()");
		//회원가입 처리작업
		//request request 한글처리
		request.setCharacterEncoding("utf-8");
		//request 파라미터 정보 가져오기
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		
		
		//자바빈 패키지  net.member.db 파일 MemberBean
		
		//MemberBean 객체 생성 mb
		MemberBean mb = new MemberBean();
		
		//파라미터 정보 => 자바빈 저장
		mb.setId(id);
		mb.setPass(pass);
		mb.setName(name);
		mb.setAge(age);
		mb.setGender(gender);
		mb.setEmail(email);
		mb.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		//디비정보lib context.xml web.xml 설정
		
		//디비 패키지 net.member.db 파일 MemberDAO;
		//메서드 호출 insertMember(mb)
		MemberDAO mdao = new MemberDAO();
		mdao.insertMember(mb);
		
		//ActionForward 이동정보를 담아서 로그인 이동 =>컨트롤러로 돌아가서 다시이동해야함
		ActionForward forward=new ActionForward();
		forward.setPath("./MemberLogin.me");//가상의 주소
		forward.setRedirect(true); //jsp로이동하면 무조건 false 가상의 주소로 넘어가면 true
		return forward;
	}
	

}
