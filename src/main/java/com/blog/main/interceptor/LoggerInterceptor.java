package com.blog.main.interceptor;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LoggerInterceptor implements HandlerInterceptor{	
	
	// LoggerAspect 클래스(Slf4j 어노테이션 사용)와 다른 방법.
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception, AccessDeniedException {
		
		log.info("======================================          START         ======================================");
		log.info(" Request URL \t:  " + request.getRequestURL());
        
		return HandlerInterceptor.super.preHandle(request, response, handler);
		
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        log.info("======================================           END          ======================================\n");
	}

}
