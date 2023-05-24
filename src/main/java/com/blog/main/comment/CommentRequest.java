package com.blog.main.comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/* 클래스의 기본 생성자 생성
 * access = 객체 생성 레벨 제한
 * (key,value) 구조로 이루어진 JSON문자열 포맷으로 데이터 전송
*/
public class CommentRequest {
	
	private Long id;
	private Long postId;
	private String contents;
	private String writer;
}
