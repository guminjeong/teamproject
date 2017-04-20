package net.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminGoodsDAO {
	//디비연결
		private Connection getConnection() throws Exception{
			Connection con=null;
//			String url="jdbc:mysql://localhost:3306/jspdb";
//			String dbuser="jspid";
//			String dbpass="jsppass";
			//1단계 드라이버로더
//			Class.forName("com.mysql.jdbc.Driver");
			//2단계 디비연결
//			con=DriverManager.getConnection(url,dbuser,dbpass);
			
			//커넥션풀 : 데이터베이스와 연결된 Connection 객체를 미리 생성하여
			//풀속에 저장해 두고 필요할때마다 이 풀에 접근하여 Connection객체 사용
			Context init=new InitialContext();
			DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/mysqlDB");
			con=ds.getConnection();
			return con;
		}
		//insertGoods(gBean)메서드
	public void insertGoods(GoodsBean gBean){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		int num=0;
		try {
			//1,2 디비연결
			con=getConnection();
			//num구하기
			sql="select max(num) from goods";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				num=rs.getInt(1)+1;
			}else{
				num=1;
			}
			//3 sql insert
			sql="insert into goods(num,category,name,content,size,color,amount,price,image,best,date) values(?,?,?,?,?,?,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2,gBean.getCategory());
			pstmt.setString(3, gBean.getName());
			pstmt.setString(4, gBean.getContent());
			pstmt.setString(5, gBean.getSize());
			pstmt.setString(6, gBean.getColor());
			pstmt.setInt(7, gBean.getAmount());
			pstmt.setInt(8, gBean.getPrice());
			pstmt.setString(9, gBean.getImage());
			pstmt.setInt(10, gBean.getBest());
			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}//메서드
	
	// getGoodsList()메서드
	public List getGoodsList(){
		List goodsList=new ArrayList();
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql
			sql="select * from goods";
			pstmt=con.prepareStatement(sql);
			//4 rs 실행 저장
			rs=pstmt.executeQuery();
			//5 rs데이터 있으면 자바빈 객체 생성 gBean
			//  rs => 자바빈 멤버변수 저장 => goodsList 한칸 저장
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
				//자바빈 => 배열 한칸 저장
				goodsList.add(gBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return goodsList;
	}
	//modifyGoods(GoodsBean goodsbean)
	public void modifyGoods(GoodsBean goodsbean){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql  num 에 해당하는
			//category name price color amount size best content수정
			sql="update goods set category=?,name=?,price=?,color=?,amount=?,size=?,best=?,content=? where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, goodsbean.getCategory());
			pstmt.setString(2, goodsbean.getName());
			pstmt.setInt(3, goodsbean.getPrice());
			pstmt.setString(4, goodsbean.getColor());
			pstmt.setInt(5, goodsbean.getAmount());
			pstmt.setString(6, goodsbean.getSize());
			pstmt.setInt(7, goodsbean.getBest());
			pstmt.setString(8, goodsbean.getContent());
			pstmt.setInt(9, goodsbean.getNum());
			//4실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}
	//deleteGoods(int num)
	public void deleteGoods(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql num해당하는 상품 삭제
			sql="delete from goods where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}
	//getGoods(int num)
	public GoodsBean getGoods(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		GoodsBean goodsbean=null;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql num에 해당하는 상품정보 가져오기
			sql="select * from goods where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//4 rs 실행 저장
			rs=pstmt.executeQuery();
			//5 데이터 있으면 자바빈객체 생성
			// rs => 자바빈 저장 
			if(rs.next()){
				goodsbean=new GoodsBean();
				goodsbean.setAmount(rs.getInt("amount"));
				goodsbean.setBest(rs.getInt("best"));
				goodsbean.setCategory(rs.getString("category"));
				goodsbean.setColor(rs.getString("color"));
				goodsbean.setContent(rs.getString("content"));
				goodsbean.setDate(rs.getString("date"));
				goodsbean.setImage(rs.getString("image"));
				goodsbean.setName(rs.getString("name"));
				goodsbean.setNum(rs.getInt("num"));
				goodsbean.setPrice(rs.getInt("price"));
				goodsbean.setSize(rs.getString("size"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return goodsbean;
	}

}//클래스
