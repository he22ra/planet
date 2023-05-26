package com.blog.main.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/* 클래스의 기본 생성자 생성
 * access = 객체 생성 레벨 제한
 * (key,value) 구조로 이루어진 JSON문자열 포맷으로 데이터 전송
*/
public class UserRequest {
	
	private int user_no;
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_nickname;
	private String user_phone;
	
}
