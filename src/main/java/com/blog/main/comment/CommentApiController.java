package com.blog.main.comment;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
