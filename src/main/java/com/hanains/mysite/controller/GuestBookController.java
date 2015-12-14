package com.hanains.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanains.mysite.service.GuestbookService;
import com.hanains.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/gs")
public class GuestBookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/list")
	public String list(Model model){
		List<GuestBookVo> list = guestbookService.getList();
		model.addAttribute("list",list);
		return "/guestbook/list";
	}
	
	@RequestMapping("/insert")
	public String insert(@ModelAttribute GuestBookVo vo){
		guestbookService.insert(vo);
		
		return "redirect:/gs/list";
	}
	
	@RequestMapping("/deleteform")
	public String deleteform(@RequestParam (value="no", required=true, defaultValue="") long no, 
			@RequestParam (value = "pwd", required=true, defaultValue="") String password, Model model){
		model.addAttribute("no", no);
		model.addAttribute("password",password);
		return "/guestbook/deleteform";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam (value="no", required=true, defaultValue="") long no, 
			@RequestParam (value = "ps", required=true, defaultValue="") String password){
		
		System.out.println("delete");
		guestbookService.delete(no, password);
		
		return "redirect:/gs/list";
	}
}
