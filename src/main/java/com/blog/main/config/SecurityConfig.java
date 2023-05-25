package com.blog.main.config;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity	// 스프링 시큐리티 필터가 스프링 필터체인에 등록
public class SecurityConfig /*extends WebSecurityConfigurerAdapter -> Deprecated 됨*/ {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		log.warn("accessDeniedHandler");
		return (request, response, e) -> {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("ACCESS DENIED");
			response.getWriter().flush();
			response.getWriter().close();
		};
	}
	
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return (request, response, e) -> {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("UNAUTHORIZED");
			response.getWriter().flush();
			response.getWriter().close();
		};
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/user/**").permitAll()
//		.antMatchers("/manager/**").authenticated()
//		.antMatchers("/admin/**").authenticated()
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/user/login.do")
		.and()
		.headers()
		.disable()
		.httpBasic()
		.disable()
//		.rememberMe()
//		.disable()
//		.logout()
//		.disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler())
		.authenticationEntryPoint(authenticationEntryPoint())
		;
		
		return http.build();
	}
//	protected void configure(HttpSecurity http) throws Exception{
//		http.csrf().disable();
//		http.authorizeRequests()
//			.antMatchers("/user/**").authenticated()
//			.antMatchers("/manager/**").access("hasRole('RORLE_ADMIN') or hasRole('ROLE_MANAGER')")
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//			.anyRequest().permitAll()
//			.and()
//			.formLogin()
//			.loginPage("/user/login")
//			;
//	}
}
 