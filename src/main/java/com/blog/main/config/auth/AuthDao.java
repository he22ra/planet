package com.blog.main.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.blog.main.user.UserResponse;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인 진행
// 로그인 완료가되면 시큐리티 session 생성(Security ContextHolder)
// 오브젝트 타입 => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 됨
// User오브젝트 타입 => UserDetails 타입 객체

// Security Session => Authentication => UserDetails(AutDao)

@SuppressWarnings("serial")
public class AuthDao implements UserDetails{
	
	@Autowired
	private UserResponse userResponse; // composition
	
	public AuthDao(UserResponse user) {
		this.userResponse = user;
	}

	// 해당 유저의 권한 return
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				String userRole = String.valueOf(userResponse.getUserRole());
				return userRole;
			}
		});
	return collect;
	}

	@Override
	public String getPassword() {
		return userResponse.getUserPwd();
	}

	@Override
	public String getUsername() {
		return userResponse.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 1년동안 로그인 안하면 휴면 계정으로 전환
		// 현재 시간 - 마지막 로그인시간 = 1년을 초과하면 return false;
		return true;
	}
	
}
