package com.blog.main.config;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity	// 스프링 시큐리티 필터가 스프링 필터체인에 등록. Security 지원 가능
public class SecurityConfig /*extends WebSecurityConfigurerAdapter -> Deprecated 됨*/ {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	// 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
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
        //http.csrf().ignoringAntMatchers("/user/**");
        
		http
		.csrf().disable()	//csrf : 정상적인 사용자가 의도치 않은 위조요청을 보내는 것
		.authorizeRequests()
		.antMatchers("/user/**").authenticated()
		.antMatchers("/manager/**").access("hasRole('1') or hasRole('2')")
		.antMatchers("/admin/**").hasRole("2")
		.anyRequest().permitAll()
		.and()
		.formLogin()	// 로그인 기능 허용
		.loginPage("/login.do")	//로그인 View 제공(GET/user/login)  //로그인 할 때 longin.html 페이지로
		.usernameParameter("userId") // 아이디 파라미터명 설정, default: username // Id input의 name과 동일하게
        .passwordParameter("userPassword") // 패스워드 파라미터명 설정, default: password // Password input의 name과 동일하게
		.loginProcessingUrl("/login") // 시큐리티가 낚아채서 대신 로그인을 진행 // 로그인의 Form Action과 동일하게
		.defaultSuccessUrl("/home") // 로그인 성공 후 이동 페이지
		.failureUrl("/login.do") // 로그인 실패 후 이동 페이지
		.and()
//		.headers().disable()
		.httpBasic().disable()
//		.rememberMe()
//		.disable()
		.logout()
		.disable()
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
 