package com.hanains.mysite.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestBookVo> getList(){
		List<GuestBookVo> list = sqlSession.selectList("guestbook.getlist");
		return list;
	}
	
	public void insert(GuestBookVo vo){
		sqlSession.insert("guestbook.insert",vo);
	}
	
	public void Delete(long no, String password){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);
		
		sqlSession.delete("guestbook.delete", map);
	}
}
