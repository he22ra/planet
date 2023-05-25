package com.blog.main.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor	// 클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 역할
public class UserController {
	
	// 로그인 페이지
	@GetMapping("/user/login.do")	//ModelAttribute : 파라미터로 수집한 객체를 자동으로 뷰(HTML)에 전달.
	public @ResponseBody String login() {
		System.out.println("UserController enter");
        return "user/login";
    }
	
	// 회원가입 페이지
	@GetMapping("/user/signup.do")	//ModelAttribute : 파라미터로 수집한 객체를 자동으로 뷰(HTML)에 전달.
	public @ResponseBody String signup() {
		System.out.println("UserController enter");
		return "user/signup";
	}
}
