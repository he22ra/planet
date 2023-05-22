package com.blog.main.post;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 	// 전체 멤버 변수가 들어간 생성자 생성
public class MessageDto {
	
	private String message;
	private String redirectUri;
	private RequestMethod method;	// HTTP 요청 메서드
	private Map<String, Object> data;	// 화면(View)으로 전달할 데이터(파라미터)
	
}
