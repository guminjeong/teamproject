package net.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.admin.goods.db.GoodsBean;
/*import net.basket.db.BasketBean;*/

public class GoodsDAO {
	//디비연결
			private Connection getConnection() throws Exception{
				Connection con=null;
//				String url="jdbc:mysql://localhost:3306/jspdb";
//				String dbuser="jspid";
//				String dbpass="jsppass";
				//1단계 드라이버로더
//				Class.forName("com.mysql.jdbc.Driver");
				//2단계 디비연결
//				con=DriverManager.getConnection(url,dbuser,dbpass);
				
				//커넥션풀 : 데이터베이스와 연결된 Connection 객체를 미리 생성하여
				//풀속에 저장해 두고 필요할때마다 이 풀에 접근하여 Connection객체 사용
				Context init=new InitialContext();
				DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/mysqlDB");
				con=ds.getConnection();
				return con;
			}
			
			//getGoodsList()
	public List getGoodsList(String item){
		List goodsList=new ArrayList();
		Connection con= null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//String sql="";
		StringBuffer sql=new StringBuffer(); //조건검색시 사용(조건에 따라  sql구문이 변한다.)
		try {
			//1,2 디비연결
			con = getConnection();
			//3 sql
			sql.append("select * from goods ");
			if(item.equals("all")){
			}else if(item.equals("best")){
				sql.append(" where best=?");
			}else{
				sql.append(" where category=?");
			}
			pstmt=con.prepareStatement(sql.toString());
			if(item.equals("all")){
			}else if(item.equals("best")){
				pstmt.setInt(1, 1);
			}else{
				pstmt.setString(1, item);
			}
			//4 rs 실행 저장
			rs=pstmt.executeQuery();
			//5 rs 데이터 있으면 자바빈 객체 생성
			// rs => 자바빈  => 배열 한칸 저장
			while(rs.next()){
				GoodsBean gBean=new GoodsBean();
				gBean.setAmount(rs.getInt("amount"));
				gBean.setBest(rs.getInt("best"));
				gBean.setCategory(rs.getString("category"));
				gBean.setColor(rs.getString("color"));
				gBean.setContent(rs.getString("content"));
				gBean.setDate(rs.getString("date"));
				gBean.setImage(rs.getString("image"));
				gBean.setName(rs.getString("name"));
				gBean.setNum(rs.getInt("num"));
				gBean.setPrice(rs.getInt("price"));
				gBean.setSize(rs.getString("size"));
				
				goodsList.add(gBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return goodsList;
	}
	// getGoods(num)
	public GoodsBean getGoods(int num){
		Connection con= null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		GoodsBean gBean=null;
		try {
			//1,2 디비연결
			con = getConnection();
			//3 sql num에 해당하는 상품가져오기
			sql="select * from goods where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//4 rs 실행 저장
			rs=pstmt.executeQuery();
			//5 rs 데이터 있으면 자바빈 객체 생성
			// rs => 자바빈 저장
			if(rs.next()){
				gBean=new GoodsBean();
				gBean.setAmount(rs.getInt("amount"));
				gBean.setBest(rs.getInt("best"));
				gBean.setCategory(rs.getString("category"));
				gBean.setColor(rs.getString("color"));
				gBean.setContent(rs.getString("content"));
				gBean.setDate(rs.getString("date"));
				gBean.setImage(rs.getString("image"));
				gBean.setName(rs.getString("name"));
				gBean.setNum(rs.getInt("num"));
				gBean.setPrice(rs.getInt("price"));
				gBean.setSize(rs.getString("size"));
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return gBean;
	}
	//updateAmount(basketList)
	/*public void updateAmount(List basketList){
		Connection con= null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			//1,2 디비연결
			con=getConnection();
			//  basketList for
			//3 sql update 
			// goods테이블 num 에 해당하는  amount 수정
			//4 실행
			for(int i=0;i<basketList.size();i++){
	BasketBean basketbean=(BasketBean)basketList.get(i);
	//3 sql
	sql="update goods set amount=amount-? where num=?";
	pstmt=con.prepareStatement(sql);
	pstmt.setInt(1, basketbean.getB_g_amount());
	pstmt.setInt(2, basketbean.getB_g_num());
	//4실행
	pstmt.executeUpdate();
			}//for
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}*/
	
	
}//클래스
