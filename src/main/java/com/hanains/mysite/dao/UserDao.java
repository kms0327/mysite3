package com.hanains.mysite.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get(String email, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", map);
		return userVo;
	}

	public void insert( UserVo vo ) {
		sqlSession.insert("user.insert",vo);
	}
	
	public UserVo get(long no){
		//회원정보 수정 할 때 사용
		UserVo vo = sqlSession.selectOne("user.getByNo",no);
		return vo;
	}
}