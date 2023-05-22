package com.blog.main.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.main.post.MessageDto;
import com.blog.main.post.PostRequest;
import com.blog.main.post.PostResponse;
import com.blog.main.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

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
    public String savePost(final PostRequest params, Model model) {
        postService.savePost(params);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }
    
    // 기존 게시글 수정
    @PostMapping("/post/update.do")
    public String updatePost(final PostRequest params, Model model) {
        postService.updatePost(params);
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }
    
    // 게시글 상세 페이지
    @GetMapping("/post/view.do")
    public String openPostView(@RequestParam final Long id, Model model) {
        PostResponse post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "post/view";
    }
    
    // 게시글 삭제
    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam final Long id, Model model) {
        postService.deletePost(id);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }
    
    // Alert 메세지 처리 후 페이지 리다이렉트
    @SuppressWarnings("unused")
	private String showMessageAndRedirect(final MessageDto params, Model model) {
    	model.addAttribute("params", params);
    	return "common/messageRedirect";
    }
    
}
