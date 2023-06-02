package com.blog.main.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/* 클래스의 기본 생성자 생성
 * access = 객체 생성 레벨 제한
 * (key,value) 구조로 이루어진 JSON문자열 포맷으로 데이터 전송
*/
public class UserRequest {
	
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userNickname;
	private String userPhone;
	
}
