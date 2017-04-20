package net.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.goods.db.GoodsDAO;

public class GoodsListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//한글처리
		request.setCharacterEncoding("utf-8");
		// item 파라미터 가저오기
		String item=request.getParameter("item");
		if(item==null){
			item="all";
		}
		//디비작업 파일 net.goods.db  GoodsDAO
		//디비객체 생성 gdao
		GoodsDAO gdao=new GoodsDAO();
		//List goodsList =메서드호출  getGoodsList();
		List goodsList=gdao.getGoodsList(item);
		//저장 goodsList
		request.setAttribute("goodsList", goodsList);
		//이동 ./goods/goods_list.jsp
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./goods/goods_list.jsp");
		return forward;
	}
}
