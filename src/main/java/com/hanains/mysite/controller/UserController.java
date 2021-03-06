package com.hanains.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired//주입
	private UserService userService;
	
	//회원가입
	@RequestMapping("/joinform")
	public String joinform(){
		return "/user/joinform";
	}
	
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo){
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess(){
		return "/user/joinsuccess";
	}
	
	@RequestMapping("/loginform")
	public String loginform(){
		return "/user/loginform";
	}
	
	@RequestMapping("/login")
	public String login(HttpSession session, @ModelAttribute UserVo vo){
		UserVo authUser = userService.login(vo);
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}
	
}
