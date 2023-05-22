package com.blog.main.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect		// AOP 기능 선언
@Component	//스프링 컨테이너에 빈(Bean)으로 등록
public class LoggerAspect {
	
	@Around("execution(* com.blog.main.controller..*Controller.*(..)) || execution(* com.blog.main.service..*Service.*(..)) || execution(* resources.mapper..*Mapper.*(..))")
	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable{
		String name = joinPoint.getSignature().getDeclaringTypeName();
		String type =
				StringUtils.contains(name, "Controller") ? " Controller  \t:  " :
                StringUtils.contains(name, "Service") ? " Service \t:  " :
                StringUtils.contains(name, "Mapper") ? " Mapper \t:  " :
                "";
		log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}
}
