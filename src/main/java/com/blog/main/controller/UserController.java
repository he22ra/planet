package com.blog.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.main.common.dto.MessageDto;
import com.blog.main.service.UserService;
import com.blog.main.user.UserRequest;
import com.blog.main.user.UserResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor	// 클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 역할
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 로그인 페이지
	@GetMapping("/login.do")
	public String loginForm() {
		System.out.println("UserController enter :: 로그인 페이지");
        return "user/login";
    }
	// 로그인 기능
	@PostMapping("/login")
	public @ResponseBody String login(@RequestBody UserRequest params) {
		System.out.println("UserController enter :: 로그인");
		String userId = params.getUser_id();
		userService.findByUserId(userId);
		return "/home";
	}
	
	// 로그인 확인 페이지
	@GetMapping("/user/setting")
	public String setting() {
		System.out.println("UserController enter :: 로그인 완료 페이지");
		return "post/test";
	}
	
	// 로그아웃
	@GetMapping("/user/logout")	
	public String logout() {
		System.out.println("UserController enter");
		return "user/logout";
	}
	
	// 회원가입 페이지
	@GetMapping("/signup.do")	
	public String signupform() {
		System.out.println("UserController enter :: 회원가입 페이지");
		return "user/signup";
	}
	
	// 회원가입 save
	@PostMapping("/signup")	
	public @ResponseBody UserResponse signup(@RequestBody final UserRequest params) {
		System.out.println("UserController enter");
		String rawPassword = params.getUser_pwd();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		params.setUser_pwd(encPassword);
		int id = userService.saveUser(params);
	    return userService.findByUserNo(id);
	}
	
	// 관리자 - Manager
	@GetMapping("/manager")	
	public String manager() {
		System.out.println("UserController enter");
		return "manager";
	}
	
	// 관리자 - admin
	@GetMapping("/admin")	
	public String admin() {
		System.out.println("UserController enter");
		return "admin";
	}
	
	// Alert 메세지 처리 후 페이지 리다이렉트
	private String showMessageAndRedirect(final MessageDto params, Model model) {
    	model.addAttribute("params", params);
    	return "common/messageRedirect";
    }
}
