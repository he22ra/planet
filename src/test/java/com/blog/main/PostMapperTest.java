package com.blog.main;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.main.post.PostMapper;
import com.blog.main.post.PostRequest;
import com.blog.main.post.PostResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
public class PostMapperTest {

		@Autowired
		PostMapper postMapper;
		
		// 게시물 작성 테스트
//		@Test
//		void save() {
//			PostRequest params = new PostRequest();
//			params.setTitle("5번 게시글 제목");
//			params.setContents("5번 게시글 내용");
//			params.setWriter("5테스터");
//			params.setNoticeYn(0);
//			postMapper.save(params);
//			
//			List<PostResponse> posts = postMapper.findAll();
//			System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");
//		}
		
		// 게시물 조회 테스트
//		@Test
//	    void findById() {
//			PostResponse post = postMapper.findById(6L);
//	        try {
//	            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
//	            System.out.println(postJson);
//
//	        } catch (JsonProcessingException e) {
//	            throw new RuntimeException(e);
//	        }
//	    }
		
		@Test
	    void update() {
	        // 1. 게시글 수정
	        PostRequest params = new PostRequest();
	        params.setId(1L);
	        params.setTitle("1번 게시글 제목 수정합니다.");
	        params.setContents("1번 게시글 내용 수정합니다.");
	        params.setWriter("수정테스터");
	        params.setNoticeYn(1);
	        postMapper.update(params);

	        // 2. 게시글 상세정보 조회
	        PostResponse post = postMapper.findById(1L);
	        try {
	            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
	            System.out.println(postJson);

	        } catch (JsonProcessingException e) {
	            throw new RuntimeException(e);
	        }
	    }
		
}
