package com.blog.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	Logger log = LoggerFactory.getLogger(this.getClass());
	
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
	public @ResponseBody Map<String, Object> loginProccess(@RequestBody UserRequest params) {
		System.out.println("UserController enter :: 로그인");
		Map<String, Object> result = new HashMap<>();
		
		// 사용자 정보 조회
		String userId = params.getUserId();
		UserResponse userInfo = userService.findByUserId(userId);
		
		if(userInfo != null) {
			log.info("==입력정보 START==");
	        log.info("MEMBER_ID_NUM : " +userInfo.getUserId());
	        log.info("==입력정보 END==");
	        
	        if(bCryptPasswordEncoder.matches(params.getUserPwd(), userInfo.getUserPwd())){
	        	if(userInfo.getUserActivate() == 1) {
	        		try {
	        			result.put("result", "Y");
					} catch (Exception e) {
						result.put("result", "NOAUTH"); // 권한 없음
					}
	        	}else if(userInfo.getUserActivate() == 0) {
	        		result.put("result", "UNUSE"); // 탈퇴유저
	        	}else {
	        		result.put("result", "N");
	        	}
	        }else {
	        	log.info("passward ");
        		result.put("result", "N");
        	}
		}else {
        	result.put("result", "N");
        }
        log.info("result :: " + result);
		
		return result;
	}
	
	// 로그인 확인 페이지
	@GetMapping("/user/info")
	public @ResponseBody String infoPage() {
		System.out.println("UserController enter :: 회원정보 페이지");
		return "회원정보조회";
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
		String rawPassword = params.getUserPwd();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		params.setUserPwd(encPassword);
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
