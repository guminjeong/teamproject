package net.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import net.board.db.BoardDAO;

public class BoardList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberList execute()");
		
		ActionForward forward = new ActionForward();
		
		//디비객체 생성 BoardDAO bdao
		BoardDAO bdao = new BoardDAO();
		
		//전체 글의 개수 구하기
		int count = bdao.getBoardCount();
		
		//한페이지에 보여줄 글의 개수 설정
		int pageSize = 10;
		
		//현페이지가 몇 페이지인지 가져오기
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1"; //pageNum없으면 무조건 1페이지
		}
		
		//시작행 구하기
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1)*pageSize + 1;
		
		//끝행 구하기
		int endRow = currentPage*pageSize;
		
		//List boardList = 메서드 호출 getBoardList(시작행, 몇개);
		List boardList =null;
		if(count != 0){
			boardList = bdao.getBoardList(startRow, pageSize); 
		}
		
		//전체 페이지수 구하기 게시판 글 50개 한화면에 보여줄 글개수 10 => 5 전체페이지
		//		게시판 글 56개 한화면에 보여줄 글개수 10 => 5 전체페이지 +1(나머지)=5
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);//+나머지페이지가없으면 0,나머지 있으면 1
		
		//한화면에 보여줄 페이지 번호 개수
		int pageBlock = 10;
		
		//시작페이지 번호 구하기 1~10=>1  11~10=>11  21~30=>21
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		
		//끝페이지 번호 구하기
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		//데이터 저장 pageCount pageBlock count boardList startPage endPage pageNum
		 
		//이동 ./board/list.jsp
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("count", count);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
	
		forward = new ActionForward();
		forward.setPath("./board/list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
