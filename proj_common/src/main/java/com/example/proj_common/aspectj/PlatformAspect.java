package com.example.proj_common.aspectj;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class PlatformAspect {


    @Pointcut("execution(* com.example.proj_common.controller.ErrorTestController.stringTest(..))")
    public void methodPointcut() {}

    @AfterReturning(value = "methodPointcut()", returning = "result")
    void process(JoinPoint joinPoint, Object result){
        Object[] args = joinPoint.getArgs();
        String str = (String) args[0];
        log.info("param -> {}", str);
        log.info("aop -> result: {}", result);
    }

}
