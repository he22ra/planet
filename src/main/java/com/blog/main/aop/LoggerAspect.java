package com.blog.main.aop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Slf4j
@Aspect		// AOP 기능 선언
@Component	//스프링 컨테이너에 빈(Bean)으로 등록
public class LoggerAspect {
	@Around("execution(* com.blog.main..*Controller.*(..)) || execution(* com.blog.main..*Service.*(..)) || execution(* src.main..*Mapper.*(..))")
	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable{
		String name = joinPoint.getSignature().getDeclaringTypeName();
		String type =
				StringUtils.contains(name, "Controller") ? "Controller ===> " :
				StringUtils.contains(name, "Service") ? "Service ===> " :
				StringUtils.contains(name, "Mapper") ? "Mapper ===> " :
				"";
		log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}
}
