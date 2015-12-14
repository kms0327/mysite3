package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.BoardJoinVo;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.GuestBookVo;

@Repository
public class BoardDao {
	private Connection getConnection(){
		Connection connection = null;
		
		try{
			//1.driver loading
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.db connect
			String dbUrl = "jdbc:oracle:thin:@localhost:1522:xe";
			connection = DriverManager.getConnection(dbUrl,"webdb","webdb");
			
		}catch(ClassNotFoundException e){
			System.out.println("드라이버 로딩 실패 - "+e);
		}catch(SQLException e){
			System.out.println("에러 - "+e);
		}
		return connection;
	}
	
	public List<BoardVo> getList(){
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			connection = getConnection();
			//3.statement make
			stmt = connection.createStatement();
			//4.sql execute
			String sql="select * from board ORDER BY no desc";
			rs = stmt.executeQuery(sql);
			//5.result get
			while(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long member_no = rs.getLong(4);
				Long view_cnt = rs.getLong(5);
				String date = rs.getString(6);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setMemberNo(member_no);
				vo.setViewCount(view_cnt);
				vo.setDate(date);
				
				list.add(vo);
			}
		}catch(SQLException e){
			System.out.println("에러 - "+e);
		}finally{
			try{
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(connection!=null)connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public BoardVo getmodifylist(long no){
		BoardVo vo = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			connection = getConnection();
			String sql="select title, content from board WHERE no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			//5.result get
			while(rs.next()){
				String title = rs.getString(1);
				String content = rs.getString(2);
				
				vo = new BoardVo();
				vo.setTitle(title);
				vo.setContent(content);
			}
		}catch(SQLException e){
			System.out.println("에러(getmodify) - "+e);
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(connection!=null)connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	public void getupdatelist(long no, String title, String content){
		BoardVo vo = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try{
			System.out.println("여기들어오나");
			connection = getConnection();
			String sql="update board set title = ?, content = ? where no = ?";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setLong(3, no);
			
			rs = pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("에러(getmodify) - "+e);
		}finally{
			try{
				if(pstmt!=null)pstmt.close();
				if(connection!=null)connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void Deletelist(long no){
		BoardVo vo = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try{
			connection = getConnection();
			
			String sql="delete from board where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("에러(getmodify) - "+e);
		}finally{
			try{
				if(pstmt!=null)pstmt.close();
				if(connection!=null)connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void viewCount(long no){
		BoardVo vo = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try{
			connection = getConnection();
			String sql="update board set view_cnt = view_cnt+1 where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("에러(getmodify) - "+e);
		}finally{
			try{
				if(pstmt!=null)pstmt.close();
				if(connection!=null)connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public List<BoardJoinVo> getJoinlist(){
		List<BoardJoinVo> list = new ArrayList<BoardJoinVo>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			connection = getConnection();
			//3.statement make
			stmt = connection.createStatement();
			//4.sql execute
			
			String sql="select a.no, a.title, a.member_no, b.name as member_name, a.view_cnt, to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss') from board a, member b where a.member_no = b.no order by a.reg_date desc";
			rs = stmt.executeQuery(sql);
			//5.result get
			while(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long member_no = rs.getLong(3);
				String member_name = rs.getString(4);
				Long view_cnt = rs.getLong(5);
				String date = rs.getString(6);
				
				BoardJoinVo vo = new BoardJoinVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setMem_no(member_no);
				vo.setMem_name(member_name);
				vo.setView_cnt(view_cnt);
				vo.setDate(date);
				
				list.add(vo);
			}
		}catch(SQLException e){
			System.out.println("에러 - "+e);
		}finally{
			try{
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(connection!=null)connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int count(){
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		int row_num = 0;
		try{
			
			connection = getConnection();
			//3.statement make
			stmt = connection.createStatement();
			//4.sql execute
			String sql="select count(*) from board";
			rs = stmt.executeQuery(sql);
			//5.result get
			while(rs.next()){
				row_num = rs.getInt(1);				
			}
		}catch(SQLException e){
			System.out.println("에러 - "+e);
		}finally{
			try{
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(connection!=null)connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return row_num;
	}
	
	public List<BoardJoinVo> page(int index, int total){
		List<BoardJoinVo> list = new ArrayList<BoardJoinVo>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			connection = getConnection();
			//3.statement make
			String sql="SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT (*) OVER () AS TOTCNT FROM (SELECT a.NO, a.TITLE, a.MEMBER_NO, b.NAME, a.VIEW_CNT, a.REG_DATE FROM board a, MEMBER b WHERE a.member_no = b.no ORDER BY a.reg_date DESC) A) WHERE RNUM > ? AND RNUM <= ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, (index-1)*total);
			pstmt.setLong(2, index * total);
			//4.sql execute
			rs = pstmt.executeQuery();
			
			//5.result get
			while(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long member_no = rs.getLong(3);
				String member_name = rs.getString(4);
				Long view_cnt = rs.getLong(5);
				String date = rs.getString(6);
				
				BoardJoinVo vo = new BoardJoinVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setMem_no(member_no);
				vo.setMem_name(member_name);
				vo.setView_cnt(view_cnt);
				vo.setDate(date);
				list.add(vo);
			}
		}catch(SQLException e){
			System.out.println("에러 - "+e);
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(connection!=null)connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void insert(BoardVo vo, long no){
		Connection connection = null;
		PreparedStatement pstmt = null;
		try{
			connection = getConnection();
			
			String sql = "insert into board values(board_no_seq.nextval,?,?,?,?,SYSDATE)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, no);
			pstmt.setLong(4, 0);
			
			pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("에러 - "+e);
		}finally{
			try{
				if(pstmt!=null) pstmt.close();
				if(connection!=null) connection.close();
			}catch(SQLException e){
				System.out.println("에러- "+e);
				e.printStackTrace();
			}
		}
	}
	
	public void Delete(GuestBookVo vo){
		Connection connection = null;
		PreparedStatement pstmt = null;
		try{
			connection = getConnection();
			
			String sql = "delete from guestbook where no =? AND password = ?";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			
			pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("에러 - "+e);
		}finally{
			try{
				if(pstmt!=null) pstmt.close();
				if(connection!=null) connection.close();
			}catch(SQLException e){
				System.out.println("에러- "+e);
				e.printStackTrace();
			}
		}
	}
}
