package net.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.GoodsBean;
import net.goods.db.GoodsDAO;

public class GoodsDetailAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// int num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
		// 디비객체 생성 gdao
		GoodsDAO gdao=new GoodsDAO();
		//자바빈 gBean = getGoods(num) 호출
		GoodsBean gBean=gdao.getGoods(num);
		//저장 "gBean"
		request.setAttribute("gBean", gBean);
		//이동  ./goods/goods_detail.jsp
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./goods/goods_detail.jsp");
		return forward;
	}
}
