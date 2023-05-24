package com.blog.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.main.common.dto.SearchDto;
import com.blog.main.common.paging.PagingResponse;
import com.blog.main.post.PostResponse;
import com.blog.main.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController	// ResponseBody 포함
@RequiredArgsConstructor
//@Controller
public class RestApiTestController {
	
	private final PostService postService;
	
	@GetMapping("/posts")
	public PagingResponse<PostResponse> findAllPost(){
		return postService.findAllPost(new SearchDto());
	}
	
//	@GetMapping("/members")
//	@ResponseBody	// 메시지 컨버터(Message Converter)에 의해 HTML이 아닌 리턴 타입에 해당하는 데이터를 리턴.
//	public List<Map<String, Object>> findAllMember() {
//		
//		List<Map<String, Object>> members = new ArrayList<>();
//		
//		for(int i = 1; i <= 20; i++) {
//			Map<String, Object> member = new HashMap<>();
//			member.put("id", i);
//			member.put("name", i+"번 개발자");
//			member.put("age", 10 + i);
//			members.add(member);
//		}
//		return members;
//	}
}
