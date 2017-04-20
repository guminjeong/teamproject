package net.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.AdminGoodsDAO;
import net.admin.goods.db.GoodsBean;

public class AdminGoodsModifyForm implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("AdminGoodsModifyForm");
		//int num가져오기
		int num=Integer.parseInt(request.getParameter("num"));
		//디비객체 생성 agdao
		AdminGoodsDAO agdao=new AdminGoodsDAO();
		//GoodsBean goodsbean = 메서드호출 getGoods(num)
		GoodsBean goodsbean=agdao.getGoods(num);
		//저장 goodsbean
		request.setAttribute("goodsbean", goodsbean);
		//이동 ./admingoods/admin_goods_modify.jsp
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./admingoods/admin_goods_modify.jsp");
		return forward;
	}
}
