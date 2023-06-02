package com.blog.main.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.main.service.UserService;
import com.blog.main.user.UserDao;
import com.blog.main.user.UserResponse;

// 시큐리티 설정에서 loginProcessingUrI("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함수 실행
@Service
public class AuthService implements UserDetailsService{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	//시큐리티 session(내부 Authentication(내부 UserDetails))
	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("user id : " + userId);
		log.info(userService.findByUserId(userId).toString());
		UserResponse user = userService.findByUserId(userId);
		
		if(user == null) {
			throw new UsernameNotFoundException(userId);
		}
		return (UserDetails) user;
	}
	
	

}
