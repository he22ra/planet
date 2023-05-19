package com.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blog.main.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor	// 클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 역할
public class PostController {

	private final PostService postService;
	
	@GetMapping("/post/write.do")
	public String openPostWrite(Model model) {
		
		String title = "제목",
               content = "내용",
               writer = "홍길동";

        model.addAttribute("t", title);
        model.addAttribute("c", content);
        model.addAttribute("w", writer);
	        
		return "post/write";
	}
}
