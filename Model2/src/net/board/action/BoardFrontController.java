package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException{
		System.out.println("doProcess()메서드 호출"); //실행되는지 확인
				
		///Model2/BoardWrite.bo
		String requestURI = request.getRequestURI(); // 가상주소 뽑아오는 메서드(URI=>호스트주소를 뺀 나머지)
		System.out.println(requestURI);

		// /Model2
		String contextPath = request.getContextPath(); // 프로젝트 이름을 뽑아오는 메서드
		System.out.println(contextPath);

		// /BoardWrite.me => 7
		System.out.println("프로젝트이름길이 : " + contextPath.length()); 
		
		String command = requestURI.substring(contextPath.length()); ///BoardWrite.bo

		System.out.println("뽑아온 가상주소 : " + command);
		
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/BoardWrite.bo")){ //가상주소 비교
			// 이동정보 저장 net.board.action.ActionForward
			forward=new ActionForward();
			forward.setPath("./board/writeForm.jsp"); //이동하기
			forward.setRedirect(false);
		
		}else if(command.equals("/BoardWriteAction.bo")){
			action = new BoardWriteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}else if(command.equals("/BoardList.bo")){
			action = new BoardList();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}else if(command.equals("/BoardContent.bo")){
			action = new BoardContentAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}else if(command.equals("/BoardUpdate.bo")){
			action = new BoardUpdate();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}else if(command.equals("/BoardUpdateAction.bo")){
			action = new BoardUpdateAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}else if(command.equals("/BoardDelete.bo")){
			forward=new ActionForward();
			forward.setPath("./board/deleteForm.jsp"); //이동하기
			forward.setRedirect(false);
		
		}else if(command.equals("/BoardDeleteAction.bo")){
			action = new BoardDeleteAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}else if(command.equals("/BoardReWrite.bo")){
			forward = new ActionForward();
			forward.setPath("./board/reWriteForm.jsp");
			forward.setRedirect(false);
		
		}else if(command.equals("/BoardReWriteAction.bo")){
			action = new BoardReWriteAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}else if(command.equals("/BoardFWrite.bo")){
			forward=new ActionForward();
			forward.setPath("./board/fwriteForm.jsp"); //이동하기
			forward.setRedirect(false);
		
		}else if(command.equals("/BoardFWriteAction.bo")){
			action = new BoardFWriteAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}
		
		else if(command.equals("/TestAction.bo")){
			action = new TestAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}else if(command.equals("/Test.bo")){
			forward=new ActionForward();
			forward.setPath("./board/test.jsp"); //이동하기
			forward.setRedirect(false);
		}
		
		//이동하기
		if (forward != null) { // 이동할 정보가 없으면 null
			if (forward.isRedirect()) { // true일때
				response.sendRedirect(forward.getPath());
			} else {// false일때
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException{
		System.out.println("doGet() 메서드 호출");
		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException{
		System.out.println("doPost() 메서드 호출");
		doProcess(request,response);
	}

}
