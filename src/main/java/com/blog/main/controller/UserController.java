package com.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor	// 클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 역할
public class UserController {
	
	// 로그인 페이지
	@GetMapping("/login.do")
	public String login() {
		System.out.println("UserController enter");
        return "user/login";
    }
	
	// 로그아웃
	@GetMapping("/user/logout")	
	public String logout() {
		System.out.println("UserController enter");
		return "user/logout";
	}
	
	// 회원가입 페이지
	@GetMapping("/signup.do")	
	public String signup() {
		System.out.println("UserController enter");
		return "user/signup";
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
}
