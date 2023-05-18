package com.blog.main;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.main.post.PostMapper;
import com.blog.main.post.PostRequest;
import com.blog.main.post.PostResponse;

@SpringBootTest
public class PostMapperTest {

		@Autowired
		PostMapper postMapper;
		
		@Test
		void save() {
			PostRequest params = new PostRequest();
			params.setTitle("5번 게시글 제목");
			params.setContents("5번 게시글 내용");
			params.setWriter("5테스터");
			params.setNoticeYn(0);
			postMapper.save(params);
			
			List<PostResponse> posts = postMapper.findAll();
			System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");
		}
}
