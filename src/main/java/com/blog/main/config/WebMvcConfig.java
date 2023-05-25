package com.blog.main.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.blog.main.interceptor.LoggerInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor())
				.excludePathPatterns("/css/**", "/images/**", "/js/**");
	}
	
	//spring security
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry){
		MustacheViewResolver resolver = new MustacheViewResolver();
		resolver.setCharset("UTF-8");
        resolver.setContentType("text/html; charset=UTF-8");
        resolver.setPrefix("classpath:templates/");
        resolver.setSuffix(".html");
		
		registry.viewResolver(resolver);
	}
}
