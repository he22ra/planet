package com.blog.main.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.main.post.PostRequest;
import com.blog.main.post.PostResponse;
import com.blog.main.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor	// 클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 역할
public class PostController {

	private final PostService postService;
	
	// 게시글 리스트 페이지
	@GetMapping("/post/list.do")
	public String openPostList(Model model) {
        List<PostResponse> posts = postService.findAllPost();
        model.addAttribute("posts", posts);
        return "post/list";
    }
	
	// 게시글 작성 페이지
	@GetMapping("/post/write.do")
	public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
		// 글 수정시
		if (id != null) {
			PostResponse post = postService.findPostById(id);
			model.addAttribute("post", post);
		}
		return "post/write";
	}
	
	// 신규 게시글 생성
    @PostMapping("/post/save.do")
    public String savePost(final PostRequest params) {
        postService.savePost(params);
        return "redirect:/post/list.do";
    }
}
