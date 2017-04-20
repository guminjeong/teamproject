package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet {

	// 메서드 오버라이딩 - 상속받은 부모의 메서드를 재정의
	// alt shift s - v

	// http://localhost:8080/Model2/*.me <-그냥 실행했을 때

	// 가상주소
	// http://localhost:8080/Model2/MemberJoin.me

	// 무조건 doProcess가호출되게 함
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doProcess() 메서드 호출");

		// 가상주소 뽑아오기
		// http://localhost:8080/Model2/MemberJoin.me

		// /Model2/MemberJoin.me //바뀌는 주소값 뽑아오는 것
		String requestURI = request.getRequestURI(); // 가상주소 뽑아오는 메서드
		System.out.println(requestURI);

		// /Model2
		String contextPath = request.getContextPath(); // 프로젝트 이름을 뽑아오는 메서드
		System.out.println(contextPath);

		// /MemberJoin.me
		System.out.println("프로젝트이름길이 : " + contextPath.length());
		String command = requestURI.substring(contextPath.length()); // contextPath.length->
																		// 7 ->
																		// 프로젝트이름
																		// 길이(/포함)

		System.out.println("뽑아온 가상주소 : " + command);

		// 뽑아온 가상주소 와 /MemberJoin.me 비교 같으면
		/// member/insertForm.jsp 이동

		// ActionForward 객체 선언
		ActionForward forward = null;
		Action action = null;

		if (command.equals("/MemberJoin.me")) { // 비교할땐 현재위치 .을 없애고 비교할떄는 현재위치
												// .을 붙여주어야한다.
			// 이동방식 가상주소 현재 위치 => .
			// 1.response 이동 => 이동은 되지만 jsp의 주소가 보임
			/* response.sendRedirect("./member/insertForm.jsp"); */

			// 2.forward A 정보를 가지고 => B 이동, 주소줄 A페이지 보임 실행화면 B페이지
			// 주소줄 http://localhost:8080/Model2/MemberJoin.me
			// 실제화면 /member/insertForm.jsp => jsp는 forward로 실제 주소가 안보이도록
			/*
			 * RequestDispatcher dispatcher =
			 * request.getRequestDispatcher("./member/insertForm.jsp");
			 * dispatcher.forward(request, response);
			 */

			// 이동정보저장(이동할 페이지 주소, 이동할 방식)
			// 이동정보 저장하는 파일 만들기
			// 패키지 net.member.action 파일 ActionForward
			// path 멤버변수 이동할 페이지 주소
			// isRedirect 멤버 변수 이동할 방식

			// ActionForward 객체 생성 기억장소 할당
			forward = new ActionForward();
			// path 이동할 페이지 주소 값 저장
			forward.setPath("./member/insertForm.jsp");
			// isRedirect 이동할 방식 저장
			forward.setRedirect(false);

		} else if (command.equals("/MemberJoinAction.me")) {
			// 회원가입 처리작업 자바파일 객체 생성 메서드 호출()
			// 처리작업할 파일의 틀을 제시 interface 만들고
			// 그틀에 맞추어서 처리파일 만들기
			// interface
			// 패키지 net.member.action 파일 Action

			// 패키지 net.member.action 파일 MemberJoinAction

			// MemberJoinAction 객체 생성
			action = new MemberJoinAction();
			// execute 메서드 호출
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} else if (command.equals("/MemberLogin.me")) {
			// 이동 ./member/loginForm.jsp
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/MemberLoginAction.me")) {
			// 패키지 net.member.action 파일이름 MemberLoginAction
			// Action 인터페이스 상속 execute( ) 메서드 오버라이딩
			// MemberLoginAction 객체 생성
			action = new MemberLoginAction();
			// forward = execute메 서드 호출
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} else if (command.equals("/Main.me")) {
			forward = new ActionForward();
			forward.setPath("./member/main.jsp");
			forward.setRedirect(false);
		
		} else if (command.equals("/MemberLogoutAction.me")) {
			// 패키지 net.member.action MemberLogout
			action = new MemberLogoutAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} else if (command.equals("/MemberInfo.me")) {
			// 패키지 net.member.action MemberInfo
			action=new MemberInfo();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		} else if(command.equals("/MemberUpdate.me")){
			action = new MemberUpdate();
			
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		} else if(command.equals("/MemberUpdateAction.me")){
			action = new MemberUpdateAction();
			
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		} else if(command.equals("/MemberDelete.me")){
			forward = new ActionForward();
			forward.setPath("./member/deleteForm.jsp");
			forward.setRedirect(false);
		
		} else if(command.equals("/MemberDeleteAction.me")){
			action =new MemberDeleteAction();
			
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		} else if(command.equals("/MemberList.me")){
			action = new MemberList();
			
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		// 이동
		/*
		 * if(이동할 정보가 있으면){ if(이동 방식 response.sendRedirect()이면 ){
		 * response.sendRedirect("이동할 주소"); }else{ RequestDispatcher dispatcher
		 * = request.getRequestDispatcher("이동할 주소값");
		 * dispatcher.forward(request, response); } }
		 */
		if (forward != null) { // 이동할 정보가 없으면 null
			if (forward.isRedirect()) { // true일때
				response.sendRedirect(forward.getPath());
			} else {// false일때
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}

	}// doProcess

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet() 메서드 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost() 메서드 호출");
		doProcess(request, response);
	}

}
