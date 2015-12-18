package com.hanains.mysite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.vo.BoardJoinVo;
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
		 
		// return "/board/list";
		
		//Page Number get
		//String index = ;
		//int index_num = page;
		//int onePageViewCount = 3;
		
		//List<BoardJoinVo> list = boardService.getList(index_num, onePageViewCount);
		//int row_num = boardService.count();
		//int pageCount = (row_num / onePageViewCount);
		
		//if(row_num % onePageViewCount != 0 ){
		//	pageCount = pageCount + 1;
		//}
	//	
	//	model.addAttribute("list",list);
	//	model.addAttribute("count",pageCount);
	//	model.addAttribute("totalCount",row_num);
	//	model.addAttribute("onePage",onePageViewCount);
	//	model.addAttribute("indexnum", index_num);

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
	
	@RequestMapping("/modify")
	public String modify(@RequestParam(value="no", required=true, defaultValue="") String no, Model model){
		
		String index = no;
		
		BoardVo vo = boardService.getmodifylist(Long.parseLong(no));
		
		model.addAttribute("vo", vo);
		model.addAttribute("index",index);

		return "/board/modify";
	}
	
	@RequestMapping("/reply")
	public String reply(@RequestParam(value="no", required=true, defaultValue="") long no, Model model){
		BoardVo vo = boardService.reply(no);
		model.addAttribute("vo",vo);
		
		System.out.println(vo);
		return "/board/write";

	}

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
	//로그인 인증 받아야 할 곳에 붙여라.
	/*@Auth
	@RequestMapping("/write")
	public String write(){
		return "/board/write";
	}
*/	@RequestMapping("/insert")
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
