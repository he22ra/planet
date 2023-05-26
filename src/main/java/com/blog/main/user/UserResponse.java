package com.blog.main.user;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class UserResponse {
	
	private int user_no;
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_nickname;
	private String user_phone;
	private LocalDateTime user_regdate;
	private int user_activate;
	private int user_role;
}
