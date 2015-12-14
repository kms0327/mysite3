package com.hanains.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardJoinVo;
import com.hanains.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardJoinVo> getList(int index, int total){
		List<BoardJoinVo> list = boardDao.page(index, total);
		return list;
	}
	
	public int count(){
		int count = boardDao.count();
		return count;
	}
	
	public BoardVo getmodifylist(long no){
		BoardVo vo = boardDao.getmodifylist(no);
		boardDao.viewCount(no);
		return vo;
	}
	
	public void getupdatelist(long no, String title, String content){
		boardDao.getupdatelist(no, title, content);
	}
	
	public void Deletelist(long no){
		boardDao.Deletelist(no);
	}
	public void insert(BoardVo vo, long no){
		boardDao.insert(vo, no);
	}
	
}
