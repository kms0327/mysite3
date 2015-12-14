package com.hanains.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.vo.BoardJoinVo;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	 
	@RequestMapping("/list")
	public String list(@RequestParam(value="index", required=true, defaultValue="") String no, Model model){
		
		
		//Page Number get
		String index = no;
		int index_num = Integer.parseInt(index);
		int onePageViewCount = 3;
		
		List<BoardJoinVo> list = boardService.getList(index_num, onePageViewCount);
		
		int row_num = boardService.count();
		int pageCount = (row_num / onePageViewCount);
		
		if(row_num % onePageViewCount != 0 ){
			pageCount = pageCount + 1;
		}
		
		model.addAttribute("list",list);
		model.addAttribute("count",pageCount);
		model.addAttribute("totalCount",row_num);

		return "/board/list";
	}
	
	@RequestMapping("/view")
	public String view(@RequestParam(value="no", required=true, defaultValue="") String no, Model model){
		//Page Number get
		String index = no;
	
		BoardVo vo = boardService.getmodifylist(Long.parseLong(no));
		
		model.addAttribute("vo", vo);
		System.out.println(index);
		model.addAttribute("index", index);
		

		return "/board/view";
	}
	
	@RequestMapping("/modify")
	public String modify(@RequestParam(value="no", required=true, defaultValue="") String no, Model model){
		
		String index = no;
		
		BoardVo vo = boardService.getmodifylist(Long.parseLong(no));
		
		model.addAttribute("vo", vo);
		model.addAttribute("index",index);

		return "/board/modify";
	}
	
	@RequestMapping("/update")
	public String update( @RequestParam(value="no", required=true, defaultValue="") long no,
			@RequestParam(value="title", required=true, defaultValue="") String tit,
			@RequestParam(value="content", required=true, defaultValue="") String cont,
			Model model){
		
		String title= tit;
		String content = cont;
		System.out.println(no+"    " + title+ "      " + content);
		boardService.getupdatelist(no, title, content);
		

		return "redirect:/board/list?index=1";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="no", required=true, defaultValue="") long no, Model model){
		boardService.Deletelist(no);
		
		return "redirect:/board/list?index=1";
	}
	
	@RequestMapping("/write")
	public String write(){
		return "/board/write";
	}
	
	@RequestMapping("/insert")
	public String insert(@ModelAttribute BoardVo vo, @RequestParam(value="memberno", required=true, defaultValue="") long no){
	//	BoardDao dao = new BoardDao();
		
		boardService.insert(vo, no);
		//dao.insert(vo);
		
		return "redirect:/board/list?index=1";
	}
}
