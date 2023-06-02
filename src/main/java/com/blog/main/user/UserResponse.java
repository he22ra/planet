package com.blog.main.user;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class UserResponse {
	
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userNickname;
	private String userPhone;
	private LocalDateTime userRegdate;
	private int userActivate;
	private int userRole;
}
