package net.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.AdminGoodsDAO;
import net.admin.goods.db.GoodsBean;
public class GoodsModifyAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GoodsModifyAction");
		//한글처리
		request.setCharacterEncoding("utf-8");
		//자바빈 객체 생성 goodsbean
		GoodsBean goodsbean=new GoodsBean();
		//폼 => 자바빈 멤버변수 저장
		// num  category name price color amount size best content
		goodsbean.setNum(Integer.parseInt(request.getParameter("num")));
		goodsbean.setCategory(request.getParameter("category"));
		goodsbean.setName(request.getParameter("name"));
		goodsbean.setPrice(Integer.parseInt(request.getParameter("price")));
		goodsbean.setColor(request.getParameter("color"));
		goodsbean.setAmount(Integer.parseInt(request.getParameter("amount")));
		goodsbean.setSize(request.getParameter("size"));
		goodsbean.setBest(Integer.parseInt(request.getParameter("best")));
		goodsbean.setContent(request.getParameter("content"));
		//디비객체 생성 agdao
		AdminGoodsDAO agdao=new AdminGoodsDAO();
		// 메서드호출 modifyGoods(goodsbean)
		agdao.modifyGoods(goodsbean);
		//이동 ./GoodsList.ag
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./GoodsList.ag");
		return forward;
	}
}
