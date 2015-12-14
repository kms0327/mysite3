package com.hanains.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.UserDao;
import com.hanains.mysite.vo.UserVo;

//business 객체
@Service
public class UserService {
	//controller 는 Service를 사용함
	@Autowired
	private UserDao userDao;
	
	public void join(UserVo vo){
		userDao.insert(vo);
	}
	
	public UserVo login(UserVo vo){
		UserVo authUser = userDao.get(vo.getEmail(), vo.getPassword());
		return authUser;
	}
}
