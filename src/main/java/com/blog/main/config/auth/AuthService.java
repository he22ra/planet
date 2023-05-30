package com.blog.main.config.auth;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.main.user.UserDao;
import com.blog.main.user.UserResponse;

// 시큐리티 설정에서 loginProcessingUrI("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함수 실행
@Service
public class AuthService implements UserDetailsService{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDao userDao;
	
	//시큐리티 session(내부 Authentication(내부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("user id : " + userId);
		UserResponse userResponse = userDao.findByUserId(userId);
		if(userResponse != null) {
			return new AuthDao(userResponse);
		}
		return null;
	}
	
	

}
