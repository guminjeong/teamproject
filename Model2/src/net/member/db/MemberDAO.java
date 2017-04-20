package net.member.db;

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
import javax.websocket.Session;

import net.member.db.MemberBean;

public class MemberDAO {
	// 멤버변수

	// 생성자

	// 메서드(멤버함수)

	// 디비연결메서드
	private Connection getConnection() throws Exception {
//		// 정보
//		String dbUrl = "jdbc:mysql://localhost:3306/jspdb2";
//		String dbId = "jspid";
//		String dbPass = "jsppass";
//		Connection con = null;
//
//		// 1단계 드라이버로더
//		Class.forName("com.mysql.jdbc.Driver");
//		// 2단계 디비연결
//		con = DriverManager.getConnection(dbUrl, dbId, dbPass);
//
//		return con;
		
		Connection con = null;
		//Context 객체생성
		Context init = new InitialContext();
		//DataSource=디비연동 이름불러오기
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/mysqlDB");
		//con=DataSource
		con=ds.getConnection();
		return con;
	}

	// 회원가입할 기능(insert)
	// 접근지정자 리턴할형 함수이름(매개변수){}
	public void insertMember(MemberBean mb) { // 매개변수 선언

		// 2단계 디비연결시 선언문은 try밖에 써주어야한다.
		Connection con = null;
		// sql 객체생성도 선언은 밖에 써주어야한다.
		PreparedStatement pstmt = null;
		String sql = "";

		try { // 예외가 발생할것같은 명령문 ==> 외부에 있는파일을 연동할때 많이쓰인다.
				// 1단계 드라이버 로더
				// 2단계 디비연결
			con = getConnection();

			// 3단계 sql 객체 생성
			sql = "insert into member(id, pass, name, reg_date, age, gender, email) values(?,?,?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, mb.getId());
			pstmt.setString(2, mb.getPass());
			pstmt.setString(3, mb.getName());
			pstmt.setTimestamp(4, mb.getReg_date());
			pstmt.setInt(5, mb.getAge());
			pstmt.setString(6, mb.getGender());
			pstmt.setString(7, mb.getEmail());
			// 4단계 => 실행에서 에러나면 sql문 에러
			pstmt.executeUpdate();
		} catch (Exception e) {
			// 예외가발생하면 실행(예외잡기 => 처리)
			e.printStackTrace();
		} finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if

		} // finally
	}

	public int idCheck(String id, String pass) {
		// 매개변수는 int boolean 등은 안에 값이 들어가지만 String등 자료형 변수는 값이 들어있는 주소값이 들어간다.
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		int check = 0;
		ResultSet rs = null;

		try {
			// 1단계 드라이버 로더
			// 2단계 디비 연결
			con = getConnection();

			// 3단계 sql 객체생성 id에 해당하는 pass가져오기
			sql = "select pass from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // ?채워넣기

			// 4단계 실행
			rs = pstmt.executeQuery();

			// 5단계 rs첫행 이동 데이터 있으면 "아이디 있음"
			// 비밀번호 비교 맞으면 check=1 틀리면 check=0
			// 없으면 "아이디 없음" check=-1
			if (rs.next() == true) {
				if (pass.equals(rs.getString("pass"))) {
					check = 1;
				} else {
					check = 0;
				}
			} else {
				check = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if

		}
		return check;
	}

	public MemberBean getMember(String id) {

		MemberBean mb = null;

		Connection con = null;
		String sql = "";
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			// 1단계 드라이버 로더
			// 2단계 디비 연결
			con = getConnection();

			// 3단계 sql 객체생성 id에 해당하는 모든정보 가져오기
			sql = "select * from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // ?채워넣기

			// 4단계 실행
			rs = pstmt.executeQuery();

			// 5단계 rs첫번째행 이동 했을때 데이터 있으면
			if (rs.next() == true) {
				// mb객체생성
				mb = new MemberBean();
				// mb안에있는 id변수에 rs에 들어있는 "id"열을 저장
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setName(rs.getString("name"));
				mb.setReg_date(rs.getTimestamp("reg_date"));
				mb.setAge(rs.getInt("age"));
				mb.setGender(rs.getString("gender"));
				mb.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if

		}

		return mb; // 주소값 리턴
	}

	public int updateMember(MemberBean mb) {

		Connection con = null;
		String sql = "";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int check = -1;

		try {
			con = getConnection();

			sql = "select pass from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getId());

			rs = pstmt.executeQuery();

			if (rs.next() == true) {
				if (mb.getPass().equals(rs.getString("pass"))) {
					sql = "update member set name=?, age=?, gender=?, email=? where id=? and pass=?";
					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, mb.getName());
					pstmt.setInt(2, mb.getAge());
					pstmt.setString(3, mb.getGender());
					pstmt.setString(4, mb.getEmail());
					pstmt.setString(5, mb.getId());
					pstmt.setString(6, mb.getPass());

					// 실행
					pstmt.executeUpdate();
					check = 1; // 수정성공

				} else {
					check = 0;// 비밀번호 틀림
				}
			} else {
				check = -1;// 아이디 없음
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if

		}

		return check;
	}
	
	public int deleteMember(String id, String pass){
		int check = -1;
		Connection con = null;
		String sql = "";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try{
			//1,2 디비연결 메서드 호출
			con= getConnection();
			
			//sql id조건에 해당하는 pass가져오기
			sql = "select pass from member where id=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			//rs=실행
			rs = pstmt.executeQuery();
			
			//rs첫행 데이터 있으면 비밀번호 비교 맞으면  check=1
			if(rs.next() == true){
				if(pass.equals(rs.getString("pass"))){
					// sql delete => 실행
					sql = "delete from member where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					
					pstmt.executeUpdate();
					check = 1;	
				}else{ //틀리면 check = 0 
					check = 0;
				}
			}else{// 없으면 check=-1 아이디 없음
				check=-1;
			}
			
		}catch(Exception e){
			
		}finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			
		}
		
		return check;
	}
	
	public List getMemberList(){
		//배열(컬렉션) 객체 생성 - 여러개의 기억공간 사용
		List memberList = new ArrayList();
		
		MemberBean mb = null;
		Connection con = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			//1,2디비연결 메서드 호출
			con=getConnection();
			//3 sql member모든 데이터 가져오기
			sql="select * from member";
			//4 rs 실행저장
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			//5 rs while 첫행 데이터 있으면
			while(rs.next()){
				//자바빈 객체생성 MemberBean mb
				mb = new MemberBean();
				//mb 멤버변수  <= rs에 id열에 해당하는 데이터 저장
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setName(rs.getString("name"));
				mb.setReg_date(rs.getTimestamp("reg_date"));
				mb.setAge(rs.getInt("age"));
				mb.setGender(rs.getString("gender"));
				mb.setEmail(rs.getString("email"));
				
				// mb주소값을 memberList 한칸에 저장
				memberList.add(mb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			// 예외상관없이 마무리 작업
			// 객체생성 닫기
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				} // Exception 이 가장 큰 범위
			} // if
			
		}
		return memberList;
		
	}
	
	
}
