package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//디비의 커넥션풀 사용하는 메소드
	public void getCon() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			
			con = ds.getConnection();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void insertBoard(BoardBean bean) {
		
		getCon();
		
		int ref=0; //글그룹을 의미
		int re_step=1;
		int re_level=1;
		
		try {
			String refsql="select max(ref) from board";
			//쿼리 실행객체
			
			pstmt =con.prepareStatement(refsql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				ref=rs.getInt(1)+1;

			}
			
			String sql="insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());
			
			pstmt.executeUpdate();
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public Vector<BoardBean> getAllBoard() {
		Vector<BoardBean> v= new Vector<>();
		getCon();
			
		try {
			String sql="select * from board order by ref desc, re_step asc";
			
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			//데이터 개수 몇개인지 몰라 while씀
			
			while(rs.next()) {
				 BoardBean bean = new BoardBean();
				 bean.setNum(rs.getInt(1));
				 bean.setWriter(rs.getString(2));
				 bean.setEmail(rs.getString(3));
				 bean.setSubject(rs.getString(4));
				 bean.setPassword(rs.getString(5));
				 bean.setReg_date(rs.getDate(6).toString());
				 bean.setRef(rs.getInt(7));
				 bean.setRe_step(rs.getInt(8));
				 bean.setRe_level(rs.getInt(9));
				 bean.setReadcount(rs.getInt(10));
				 bean.setContent(rs.getString(11));
				
				v.add(bean);
				
				
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
			return v;
			
	//하나의 게시글을 리턴
	
	
	}
	
	public BoardBean getOneBoard(int num) {
		BoardBean bean = new BoardBean();
		getCon();
		
		try {
			//조회수 증가 쿼리
			String readsql ="update board set readcount=readcount+1 where num=?";
			pstmt = con.prepareStatement(readsql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			
			//쿼리준비
			String sql="select * from board where num=?";
			//쿼리실행객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			//쿼리 실행 후 결과 리턴
			rs=pstmt.executeQuery();
			if (rs.next()) {
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				
			
			}
			
			//만약 실행 후 결과리턴다음값이 또 있다면.. 저장
			
			
			
			//콘솔 반납
			
			con.close();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
		
	}
	
	//답글글 저장되는 메서드 생성
	
	public void reWriteBoard(BoardBean bean) {
		//부모글그룹과 글레벨 글스텝을 읽어드림 증가위해
		
		int ref = bean.getRef();
		int re_step = bean.getRe_step();
		int re_level = bean.getRe_level();
		
		getCon();
		
		
		
		try {
			
			//부모글보다 큰 re_level의 값을 전부 1씩 증가
			String levelsql="update board set re_level=re_level+1 where ref=? and re_level > ?";
			
			//쿼리실행객체 선언
			pstmt = con.prepareStatement(levelsql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, re_level);
			
			//쿼리 실행
			
			pstmt.executeUpdate();
			
			//답변글 데이터를 저장
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			
			//?에 값을 대입
			
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step+1);
			pstmt.setInt(7, re_level+1);
			pstmt.setString(8, bean.getContent());
			
			pstmt.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

}
