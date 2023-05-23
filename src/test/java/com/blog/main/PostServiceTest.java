package com.blog.main;

import com.blog.main.post.PostRequest;
import com.blog.main.service.PostService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {
	
	@Autowired
    PostService postService;
	
//	@Test
//    void save() { 
//        PostRequest params = new PostRequest();
//        params.setTitle("0519-6번 게시글 제목");
//        params.setContents("0519-6번 게시글 내용");
//        params.setWriter("0519-6테스터");
//        params.setNoticeYn(0);
//        Long id = postService.savePost(params);
//        System.out.println("생성된 게시글 ID : " + id);
//    }
	
//	게시글 100개 등록
	@Test
    void saveByForeach() {
        for (int i = 1; i <= 100; i++) {
            PostRequest params = new PostRequest();
            params.setTitle(i + "번 게시글 제목");
            params.setContents(i + "번 게시글 내용");
            params.setWriter("테스터" + i);
            params.setNoticeYn(0);
            postService.savePost(params);
        }
    }
    
}
