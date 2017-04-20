package net.member.action;

public class ActionForward {
	
	//이동정보저장(이동할 페이지 주소, 이동할 방식)
	//이동정보 저장하는 파일 만들기
	//패키지 net.member.action 파일  ActionForward
	//path 멤버변수 이동할 페이지 주소
	//isRedirect 멤버 변수 이동할 방식
	
	private String path; //이동할 페이지주소
	private boolean isRedirect;	//이동할 방식 true sendRedirect
								//		  false forward이동
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
}	
