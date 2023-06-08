package com.blog.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.main.user.UserResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor	// 클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 역할
public class HomeController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/home")
	public String goHome(HttpServletRequest request) {
		System.out.println("Home enter");
		// 사용자 정보 조회
		UserResponse params = new UserResponse();
		
		params = (UserResponse) request.getAttribute("userRole");
		if(params != null) {
			log.info(params.toString());
		}
		return "common/home";
	}

}