package net.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.ConnectionPropertiesTransform;

public class BoardDAO {
	
//디비작업 1,2단계 분리 함수
	private Connection getConnection() throws Exception{
//		String dbUrl = "jdbc:mysql://localhost:3306/jspdb2";
//		String dbId="jspid";
//		String dbPass="jsppass";
//		Connection con = null;
//		
//		//1단계 드라이버 로더
//		Class.forName("com.mysql.jdbc.Driver");
//		
//		//2단계 디비연결
//		con = DriverManager.getConnection(dbUrl, dbId, dbPass);
//		
//		return con;
		
		//커넥션 풀(Connection Pool)
		//연결정보를 저장하는 기억공간
		//데이터베이스와 연결된 Connection 객체를 미리 생성하여
		//풀(Pool)속에 저장해 두고 필요할때마다 이풀을 접근하여 Connection객체사용
		//작업이 끝나면 다시반환
		
		//자카르타 DBCP API 이용한 커넥션 풀
		//http://commons.apache.org/ 다운 => 아파치 톰캣에서 지원 안넣어주어도됨
		//WebCOntent - WEB_INF - lib
		//commons-collections-3.2.1.jar
		//commons-dbcp-1.4.jar
		//commons-pool-1.6.jar
		
		//1.WebContent - META-INF - context.xml만들기
		// 	1단계 , 2단계 기술 -> 디비연동 이름 정의
		//2.WebContent - WEB-INF - web.xml수정
		//	context.xml 에 디비연동 해놓은 이름을 모든페이지에 알려줌
		//3.DB작업(DAO) - 이름을 불러서 사용
		
		Connection con = null;
		//Context 객체생성
		Context init = new InitialContext();
		//DataSource=디비연동 이름불러오기
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/mysqlDB");
		//con=DataSource
		con=ds.getConnection();
		return con;
		
	}
	
//글쓰기 함수
	public void insertBoard(BoardBean bb){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		int num=0;
		try {
			//1,2 디비연결 메서드 호출
			con = getConnection();
			//num 게시판 글번호 구하기
			sql ="select max(num) from board";
			pstmt= con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				num=rs.getInt(1)+1;
			}
//			System.out.println("num="+num);
			
			//3 sql insert 디비날짜 now() = > 현재 시스템의 날짜, 시간을 가져옴
			sql = "insert into board(num,name,pass,subject,content,readcount,re_ref,re_lev,re_seq,date,ip,file) values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num); //전체글에서 가장큰 번호에 +1
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPass());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setInt(6, 0); //readcount 조회수
			pstmt.setInt(7, num);//re_ref 답변글 그룹 //일반글을 기준으로 그번호가 그룹번호가 됨 ==일반글의 글번호와 같게 입력
			pstmt.setInt(8, 0);//re_lev 답변글 들여쓰기 //그룹번호내 기존글 +1 //일반글 들여쓰기 없음
			pstmt.setInt(9, 0);//re_seq 답변글 순서 //그룹번호내 기존글 +1 //일반글 순서 맨위
			pstmt.setString(10, bb.getIp());
			pstmt.setString(11, bb.getFile());
			
			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (rs != null) {try {rs.close();} catch (SQLException ex) {}} 
			if (pstmt != null) {try {pstmt.close();} catch (SQLException ex) {}} 
			if (con != null) {try {con.close();} catch (SQLException ex) {}} 
		} // finally
	}//insertBoard(bb)
	
//전체 글의 개수 구하는 함수
	public int getBoardCount(){
		Connection con = null;
		String sql ="";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			//1,2디비연결 메서드 호출
			con = getConnection();
			//3 sql 함수 count(*)이용
			sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			//4 rs 실행 저장
			rs = pstmt.executeQuery();
			//5 rs값이있으면 count에 저장
			if(rs.next()){
				count=rs.getInt(1);
			}
			
//			System.out.println("count="+count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (rs != null) {try {rs.close();} catch (SQLException ex) {}} 
			if (pstmt != null) {try {pstmt.close();} catch (SQLException ex) {}} 
			if (con != null) {try {con.close();} catch (SQLException ex) {}} 
		} // finally
		return count;
	}//getBoardCount()
	
//글목록 보여주는 함수
	public List getBoardList(int startRow, int pageSize){ //시작하는 행번호 , 개수
		List boardList = new ArrayList();
		Connection con = null;
		String sql = "";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		BoardBean bb = null;
		
		try {
			//디비연결
			con = getConnection();
			
			//sql
			sql = "select * from board order by re_ref desc, re_seq asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1); //시작행-1
			pstmt.setInt(2, pageSize); //몇개글
			
			//rs
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//객체생성
				bb= new BoardBean();
				bb.setNum(rs.getInt("num"));
				bb.setName(rs.getString("name"));
				bb.setSubject(rs.getString("subject"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setDate(rs.getDate("date"));
				bb.setIp(rs.getString("ip"));
				bb.setPass(rs.getString("pass"));
				bb.setContent(rs.getString("content"));
				bb.setFile(rs.getString("file"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));
				
	
				//한칸에저장
				boardList.add(bb);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (rs != null) {try {rs.close();} catch (SQLException ex) {}} 
			if (pstmt != null) {try {pstmt.close();} catch (SQLException ex) {}} 
			if (con != null) {try {con.close();} catch (SQLException ex) {}} 
		}
			
		return boardList;
	}
	
//글보기
	public BoardBean getBoard(int num){
		BoardBean bb = null;
		Connection con = null;
		String sql = "";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			//디비연결
			con=getConnection();
			
			sql="select * from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bb = new BoardBean();
				bb.setNum(rs.getInt("num"));
				bb.setName(rs.getString("name"));
				bb.setSubject(rs.getString("subject"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setDate(rs.getDate("date"));
				bb.setIp(rs.getString("ip"));
				bb.setPass(rs.getString("pass"));
				bb.setContent(rs.getString("content"));
				bb.setFile(rs.getString("file"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (rs != null) {try {rs.close();} catch (SQLException ex) {}} 
			if (pstmt != null) {try {pstmt.close();} catch (SQLException ex) {}} 
			if (con != null) {try {con.close();} catch (SQLException ex) {}} 
		}	
		return bb;
	}

//조회수 증가하는 함수
	public void updateReadcount(int num){

		Connection con = null;
		String sql ="";
		PreparedStatement pstmt = null;
	
		try {
			//1,2단계
			con=getConnection();
			
				sql="update board set readcount=readcount+1 where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//실행
				pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (pstmt != null) {try {pstmt.close();} catch (SQLException ex) {}} 
			if (con != null) {try {con.close();} catch (SQLException ex) {}} 
		} // finally
		
	}

//글 수정하는 함수
	public int updateBoard(BoardBean bb){
		int check=0;
		Connection con = null;
		String sql ="";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1,2디비연결메서드 호출
			con = getConnection();
			
			//3 sql num에 해당하는 pass가져오기
			sql="select pass from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bb.getNum());
			
			//4 rs실행저장
			rs = pstmt.executeQuery();
			
			//5 rs데이터 있으면
			if(rs.next()){
				//비밀번호 비교 맞으면 check=1;
				if(bb.getPass().equals(rs.getString("pass"))){
					// 3 num에 해당하는 name, subject, content수정
					sql="update board set name=?, subject=?, content=? where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, bb.getName());
					pstmt.setString(2, bb.getSubject());
					pstmt.setString(3, bb.getContent());
					pstmt.setInt(4, bb.getNum());
					
					//4 실행
					pstmt.executeUpdate();
					check = 1;
					
				}else{ //틀리면 check=0;
					check=0;
				}
			}else{//없으면 check=-1;
				check=-1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (rs != null) {try {rs.close();} catch (SQLException ex) {}} 
			if (pstmt != null) {try {pstmt.close();} catch (SQLException ex) {}} 
			if (con != null) {try {con.close();} catch (SQLException ex) {}} 
		} // finally
		
		return check;
		
	}

//글 삭제하는 함수
	public int deleteBoard(String pass,int num){
		int check=-1;
		Connection con = null;
		String sql = "";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			//1,2디비연결 메서드 호출
			con = getConnection();
			
			//3 sql num에 해당하는 pass 가져오기
			sql="select pass from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			//4 rs 실행 저장
			rs = pstmt.executeQuery();
			
			//5 rs 데이터 있으면
			if(rs.next()){
				//비밀번호 비교 맞으면 check=1
				if(pass.equals(rs.getString("pass"))){
					//3 num에 해당하는 게시판 글 삭제
					sql="delete from board where num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, num);
					
					//4 실행
					pstmt.executeUpdate();
					check=1;	
				}else{ //틀리면 check=0
					check=0;
				}		
			}else{//없으면 check=-1
				check=-1;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (rs != null) {try {rs.close();} catch (SQLException ex) {}} 
			if (pstmt != null) {try {pstmt.close();} catch (SQLException ex) {}} 
			if (con != null) {try {con.close();} catch (SQLException ex) {}} 
		}	
		return check;
	}

//답글 함수
	public void reInsertBoard(BoardBean bb){
		Connection con = null;
		String sql = "";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int num = 0;
		
		try {
			//1,2디비연결
			con = getConnection();
	
			//3 sql select 최대 num구하기
			sql="select max(num) from board";
			pstmt=con.prepareStatement(sql);
			
			//4 실행
			rs = pstmt.executeQuery();
			
			//5 rs데이터 있으면 1번째열을 가져와서 +1
			if(rs.next()){
				num = rs.getInt(1)+1;
			}
			
			//답글 순서 재배치 
			//3 update조건 re_ref 같은 그룹 re_seq 기존값보다 큰값이 있으면
			sql ="update board set re_seq=re_seq+1 where re_ref=? and re_seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bb.getRe_ref());
			pstmt.setInt(2, bb.getRe_seq());
			// re_seq 1증가
			
			//4실행
			pstmt.executeUpdate();
			
			//3 sql insert num 구한값	 re_ref 그대로
			//			   re_lev+1  re_seq+1
			sql="insert into board(num,name,pass,subject,content,readcount,re_ref,re_lev,re_seq,date,ip,file) values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPass());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setInt(6, 0);//readcount 조회수 0
			pstmt.setInt(7, bb.getRe_ref()); //re_ref 기존글 그룹번호 같게함
			pstmt.setInt(8, bb.getRe_lev()+1); //re_lev 답변글 들여쓰기 기존글+1
			pstmt.setInt(9, bb.getRe_seq()+1); //re_seq 답변글 순서 기존글+1
			pstmt.setString(10, bb.getIp());
			pstmt.setString(11, bb.getFile());
			
			//4 실행
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (rs != null) {try {rs.close();} catch (SQLException ex) {}} 
			if (pstmt != null) {try {pstmt.close();} catch (SQLException ex) {}} 
			if (con != null) {try {con.close();} catch (SQLException ex) {}} 
		}	
	}
	
}//class
