package com.example.proj_common.aspectj;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Aspect
@Slf4j
public class PlatformAspect {


    @Pointcut("execution(* com.example.proj_common.controller.ErrorTestController.stringTest(..))")
    public void methodPointcut() {}


    @Before(value = "methodPointcut()")
    void doBefore(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String str = (String) args[0];
        if (!StringUtils.hasText(str)){
            return;
        }

        System.out.println(str);

    }

//    @AfterReturning(value = "methodPointcut()", returning = "result")
//    void process(JoinPoint joinPoint, Boolean result){
//        if (!result){
//            log.info("result is false -> {}" ,result);
//            return;
//        }
//        Object[] args = joinPoint.getArgs();
//        String str = (String) args[0];
//        log.info("param -> {}", str);
//        log.info("aop -> result: {}", result);
//    }

}
