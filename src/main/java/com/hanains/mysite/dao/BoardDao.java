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
	
	public void insert(BoardVo vo, long no, long checkNo, long maxNum){
		vo.setMemberNo(no);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vo", vo);
		map.put("checkNo", checkNo);
		map.put("maxNum", maxNum);
		System.out.println("dao "+vo.getGroupNo()+" "+vo.getOrderNo()+" "+vo.getDepth());

		sqlSession.insert("board.insert",map);
	}
	
	public BoardVo reply(long no){
		BoardVo vo = sqlSession.selectOne("board.reply", no);
		return vo;
	}
	public long max(){
		long maxNum = sqlSession.selectOne("board.max");
		System.out.println(maxNum);
		return maxNum;
	}
}
