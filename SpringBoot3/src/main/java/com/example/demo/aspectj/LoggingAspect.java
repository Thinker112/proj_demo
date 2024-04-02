package com.example.demo.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Around("@annotation(com.example.demo.annotation.Log)")
    public Object aroundWebMethods(ProceedingJoinPoint pjp) throws Throwable {
        // 在执行前设置traceId
        String traceId = UUID.randomUUID().toString();
        MDC.put("traceId", traceId);

        try {
            // 继续执行原方法
            for (Object arg : pjp.getArgs()) {
                log.info("入参：{}", arg);
            }

            return pjp.proceed();
        } finally {
            // 方法执行后清除traceId
            MDC.remove("traceId");
        }
    }
}
