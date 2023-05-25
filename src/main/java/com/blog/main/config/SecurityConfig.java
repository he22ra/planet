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
@EnableWebSecurity	// 스프링 시큐리티 필터가 스프링 필터체인에 등록. Security 지원 가능
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
		/* permitAll : login 없이 허용
		 * authenticated : 인증 필요
		*/
		// 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
        http.csrf().ignoringAntMatchers("/user/**");
        
		http
		.csrf().disable()	//csrf : 정상적인 사용자가 의도치 않은 위조요청을 보내는 것
		.authorizeRequests()
		.antMatchers("/user/**").permitAll()
//		.antMatchers("/manager/**").authenticated()
//		.antMatchers("/admin/**").authenticated()
		.anyRequest().authenticated()
		.and()
		.formLogin()	// 로그인 기능 허용
		.loginPage("/user/login")	//로그인 View 제공(GET/user/login)  //로그인 할 때 longin.html 페이지로
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
 