package com.blog.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.main.comment.CommentRequest;
import com.blog.main.comment.CommentResponse;
import com.blog.main.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController	//ResponseBody 포함
@RequiredArgsConstructor
public class CommentApiController {

	private final CommentService commentService;
	
	//신규 댓글 작성
	/* PathVariable : REST API에서 리소스를 표현. URI에서 파라미터 전달 -> {postId}
	*/
	@PostMapping("/post/{postId}/comments")
	public CommentResponse saveComment(@PathVariable final Long postId, 
									   @RequestBody final CommentRequest params) {
		Long id = commentService.saveComment(params);
		return commentService.findCommentById(id);
	}
	
	// 댓글 리스트 조회
    @GetMapping("/post/{postId}/comments")
    public List<CommentResponse> findAllComment(@PathVariable final Long postId) {
        return commentService.findAllComment(postId);
    }
    
    // 댓글 상세정보 조회
    @GetMapping("/post/{postId}/comments/{id}")
    public CommentResponse findCommentById(@PathVariable final Long postId, @PathVariable final Long id) {
        return commentService.findCommentById(id);
    }
    
    // 기존 댓글 수정
    @PatchMapping("/post/{postId}/comments/{id}")
    public CommentResponse updateComment(@PathVariable final Long postId, @PathVariable final Long id, @RequestBody final CommentRequest params) {
        commentService.updateComment(params);
        return commentService.findCommentById(id);
    }
    
    // 댓글 삭제
    @DeleteMapping("/post/{postId}/comments/{id}")
    public Long deleteComment(@PathVariable final Long postId, @PathVariable final Long id) {
        return commentService.deleteComment(id);
    }
}
