package com.lms.loanplans.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
 
@Aspect
@Component
public class LoggingAspect {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Pointcut("execution(* com.crm.meeting.service.*.*(..)) || execution(* com.crm.meeting.controller.*.*(..)) || execution(* com.crm.meeting.repository.*.*(..))")
    private void applicationMethods() {
        // Pointcut for methods in service, controller, and repository packages
    }
 
    @Before("applicationMethods()")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        logger.info("Executing method: {}", joinPoint.getSignature());
    }
 
    @AfterReturning(pointcut = "applicationMethods()", returning = "result")
    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
        logger.info("Method executed: {} with result: {}", joinPoint.getSignature(), result);
    }
 
    @AfterThrowing(pointcut = "applicationMethods()", throwing = "exception")
    public void logAfterMethodThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Method executed: {} with exception: {}", joinPoint.getSignature(), exception.getMessage());
    }
 
}
 
 
 