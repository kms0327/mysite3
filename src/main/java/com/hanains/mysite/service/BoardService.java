package com.hanains.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardJoinVo;
import com.hanains.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	private final int LIST_PAGESIZE = 5; // 페이지당 게시물 수 
	private final int LIST_BLOCKSIZE = 5; //아래 페이지 수
	
	
	public Map<String, Object> listBoard(String searchKeyword, long page){
		
		long totalCount = boardDao.getCount(searchKeyword);
		long pageCount = (long)Math.ceil((double) totalCount / LIST_PAGESIZE);
		long blockCount = (long)Math.ceil((double) pageCount / LIST_BLOCKSIZE);
		long currentBlock = (long)Math.ceil((double)page/LIST_BLOCKSIZE);
		
		if(page<1){
			page = 1L;
			currentBlock = 1;
		}else if(page>pageCount){
			page = pageCount;
			currentBlock = (int)Math.ceil((double)page/LIST_BLOCKSIZE);
		}
		
		long startPage = (currentBlock == 0 )? 1: (currentBlock-1)*LIST_BLOCKSIZE +1;
		long endPage = (startPage -1)+LIST_BLOCKSIZE;
		long prevPage = (currentBlock > 1) ?(currentBlock -1) * LIST_BLOCKSIZE : 0;
		long nextPage = (currentBlock < blockCount)?currentBlock*LIST_BLOCKSIZE+1:0;
		
		List<BoardVo> list= boardDao.getList(searchKeyword, page, LIST_PAGESIZE);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("searchKeyWord", searchKeyword);
		map.put("firstItemIndex", totalCount-(page-1)*LIST_PAGESIZE);
		map.put("currentPage", page);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		
		return map;
	}
	
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
	public void insert(BoardVo vo, long no, long checkNo, long maxNum){
		boardDao.insert(vo, no, checkNo, maxNum);
		System.out.println("service  "+vo+" "+no+" "+checkNo);

		
	}
	
	public BoardVo reply(long no){
		BoardVo vo = boardDao.reply(no);
		
		if(vo.getGroupNo()==0){
			vo.setGroupNo(1);
		}
		if(vo.getOrderNo()==0){
			vo.setOrderNo(1);
		}
		if(vo.getDepth()==0){
			vo.setDepth(1);
		}
		/*int orderNo = (int) vo.getOrderNo() + 1;
		int depth = (int) vo.getDepth() + 1;
		vo.setOrderNo(orderNo);
		vo.setDepth(orderNo);*/
		
		return vo;
	}
	
	public long max(){
		long maxNum = boardDao.max();
		System.out.println("ser "+ maxNum);
		return maxNum;
	}
	
}
