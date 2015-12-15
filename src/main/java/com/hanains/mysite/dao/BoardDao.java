package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.BoardJoinVo;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.GuestBookVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
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
	
	public List<BoardJoinVo> getJoinlist(){
		List<BoardJoinVo> list = sqlSession.selectList("board.getjoinlist");
		return list;
	}
	
	public BoardVo getmodifylist(long no){
		BoardVo vo = sqlSession.selectOne("board.viewmodify", no);
		return vo;
	}
	
	public void getupdatelist(long no, String title, String content){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("title", title);
		map.put("content", content);
		
		sqlSession.update("board.update", map);
	}
	
	public void Deletelist(long no){
		sqlSession.delete("board.delete", no);
	}
	
	public void viewCount(long no){
		sqlSession.update("board.viewcount",no);
	}
	
	public int count(){
		int row_num = sqlSession.selectOne("board.count");
		return row_num;
	}
	
	public List<BoardJoinVo> page(int index, int total){
		Map<String, Object> map = new HashMap<String, Object>();
		int num1 = (index-1)-total;
		int num2 = index*total;
		map.put("index", (index-1)*total);
		map.put("total", index*total);
		
		List<BoardJoinVo> list = sqlSession.selectList("board.page", map);
		return list;
	}
	
	public void insert(BoardVo vo, long no){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vo", vo);
		map.put("no", no);
		sqlSession.insert("board.insert",map);
	}
}
