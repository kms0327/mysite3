package com.hanains.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanains.mysite.annotation.Auth;
import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	 
	@RequestMapping("/list")
	public String list(
			 Model model,
			@RequestParam(value="kw", required=true, defaultValue="") String searchKeyword,
			@RequestParam( value="p", required = true, defaultValue = "1" ) long page){
		
		Map<String, Object> map = boardService.listBoard(searchKeyword, page);
		System.out.println(map);
		model.addAttribute("listData", map);
		 
		return "/board/list";
	}
	
	@RequestMapping("/view")
	public String view(@RequestParam(value="no", required=true, defaultValue="") String no, Model model){
		//Page Number get
		String index = no;
	
		BoardVo vo = boardService.getmodifylist(Long.parseLong(no));
		model.addAttribute("vo", vo);
		model.addAttribute("index", index);
		

		return "/board/view";
	}
	
	@Auth
	@RequestMapping("/modify")
	public String modify(@RequestParam(value="no", required=true, defaultValue="") String no, Model model){
		
		String index = no;
		
		BoardVo vo = boardService.getmodifylist(Long.parseLong(no));
		
		model.addAttribute("vo", vo);
		model.addAttribute("index",index);

		return "/board/modify";
	}
	
	@Auth
	@RequestMapping("/reply")
	public String reply(@RequestParam(value="no", required=true, defaultValue="") long no, Model model){
		BoardVo vo = boardService.reply(no);
		model.addAttribute("vo",vo);
		
		System.out.println(vo);
		return "/board/write";

	}
	
	@Auth
	@RequestMapping("/update")
	public String update( @RequestParam(value="no", required=true, defaultValue="") long no,
			@RequestParam(value="title", required=true, defaultValue="") String tit,
			@RequestParam(value="content", required=true, defaultValue="") String cont,
			Model model){
		
		String title= tit;
		String content = cont;
		boardService.getupdatelist(no, title, content);
		

		return "redirect:/board/list?index=1";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="no", required=true, defaultValue="") long no, Model model){
		boardService.Deletelist(no);
		
		return "redirect:/board/list?index=1";
	}
	
	@Auth
	@RequestMapping("/write")
	public String write(@RequestParam(value="no", required=true, defaultValue="0") long no, Model model
			,@RequestParam(value="groupNo", required=true, defaultValue="0") long groupNo
			,@RequestParam(value="orderNo", required=true, defaultValue="0") long orderNo
			,@RequestParam(value="depth", required=true, defaultValue="0") long depth){
		
			model.addAttribute("no",no);
			model.addAttribute("groupNo",groupNo);
			model.addAttribute("orderNo",orderNo);
			model.addAttribute("depth",depth);
		
		return "/board/write";
	}
	
	@RequestMapping("/insert")
	public String insert(@ModelAttribute BoardVo vo, 
			@RequestParam(value="memberno", required=true, defaultValue="") long no,
			@RequestParam(value="checkNo", required=true,defaultValue="0") long checkNum){
		
		long maxNum = boardService.max();
		System.out.println(maxNum);
		if(checkNum != 0){
			boardService.insert(vo, no, checkNum, maxNum);
			System.out.println("controller  "+vo+" "+no+" "+checkNum);
		}else if(checkNum ==0){//처음글쓸때
			boardService.insert(vo, no, checkNum, maxNum);
		}
		return "redirect:/board/list?index=1";
	}
}
