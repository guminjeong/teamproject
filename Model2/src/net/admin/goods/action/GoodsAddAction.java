package net.admin.goods.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.admin.goods.db.AdminGoodsDAO;
import net.admin.goods.db.GoodsBean;

public class GoodsAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// upload 폴더 만들기  5*1024*1024
		// MultipartRequest 객체 생성
		ServletContext context=request.getServletContext();
		String realPath=context.getRealPath("/upload");
		int maxSize=5*1024*1024;
		MultipartRequest multi=new MultipartRequest(request, realPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		// 자바빈 파일 만들기 net.admin.goods.db 파일 GoodsBean
		//자바빈 파일 생성 GoodsBean gBean
		GoodsBean gBean=new GoodsBean();
		// 폼 => 자바빈 저장
		gBean.setAmount(Integer.parseInt(multi.getParameter("amount")));
		gBean.setBest(0);
		gBean.setCategory(multi.getParameter("category"));
		gBean.setColor(multi.getParameter("color"));
		gBean.setContent(multi.getParameter("content"));
		String image=multi.getFilesystemName("file1")+","+multi.getFilesystemName("file2")+","+multi.getFilesystemName("file3")+","+multi.getFilesystemName("file4");
		gBean.setImage(image);
		gBean.setName(multi.getParameter("name"));
		gBean.setPrice(Integer.parseInt(multi.getParameter("price")));
		gBean.setSize(multi.getParameter("size"));
		System.out.println(realPath);
		System.out.println(image);
		// 디비 파일 만들기 net.admin.goods.db 파일 AdminGoodsDAO
		// 객체 생성 AdminGoodsDAO agdao
		AdminGoodsDAO agdao=new AdminGoodsDAO();
		//객체 생성 메서드호출 insertGoods(gBean)
		agdao.insertGoods(gBean);
		// 이동 ./GoodsList.ag
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./GoodsList.ag");
		return forward;
	}
}
