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
		@Test
		void save() {
			PostRequest params = new PostRequest();
			params.setTitle("0519-4번 게시글 제목");
			params.setContents("0519-4번 게시글 내용");
			params.setWriter("0519-4테스터");
			params.setNoticeYn(0);
			postMapper.save(params);
			
//			List<PostResponse> posts = postMapper.findAll();
//			System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");
			Long id = params.getId();
			System.out.println("최근 게시글 id는 : " + id + "번입니다.");
		}
		
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
		
//		@Test
//	    void update() {
//	        // 1. 게시글 수정
//	        PostRequest params = new PostRequest();
//	        params.setId(1L);
//	        params.setTitle("1번 게시글 제목 수정합니다.");
//	        params.setContents("1번 게시글 내용 수정합니다.");
//	        params.setWriter("수정테스터");
//	        params.setNoticeYn(1);
//	        postMapper.update(params);
//
//	        // 2. 게시글 상세정보 조회
//	        PostResponse post = postMapper.findById(1L);
//	        try {
//	            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
//	            System.out.println(postJson);
//
//	        } catch (JsonProcessingException e) {
//	            throw new RuntimeException(e);
//	        }
//	    }
		
		// 삭제 여부(delete_yn) 상태 값을 0(false)에서 1(true)로 UPDATE
		// 추후에 게시글 리스트 페이지에는 delete_yn이 0(false)인 데이터만 사용자에게 노출
//	    @Test
//	    void delete() {
//	        System.out.println("삭제 이전의 전체 게시글 개수는 : " + postMapper.findAll().size() + "개입니다.");
//	        postMapper.deleteById(1L);
//	        System.out.println("삭제 이후의 전체 게시글 개수는 : " + postMapper.findAll().size() + "개입니다.");
//	    }
		
		// 최근게시물 조회 테스트
//		@Test
//	    void findRecentlyPostId() {
//			Long recentlyPost = postMapper.findRecentlyPostId();
//	        try {
//	            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(recentlyPost);
//	            System.out.println(postJson);
//
//	        } catch (JsonProcessingException e) {
//	            throw new RuntimeException(e);
//	        }
//	    }
		
}
