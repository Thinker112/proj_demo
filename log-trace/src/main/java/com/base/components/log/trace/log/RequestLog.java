package com.base.components.log.trace.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * @author xianfeng
 * @date 2023/5/13 18:20
 */
@Slf4j
@Aspect
@Order(HIGHEST_PRECEDENCE)
public class RequestLog {
    @Pointcut("execution(public * com..controller..*.*(..))*")
    public void anyController() {
    }

    @Around("anyController()")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 请求URL
        Object result = null;
        long startTime = System.currentTimeMillis();
        try {
            requestURI();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            // 类路径URL
            String methodPackage = methodSignature.getDeclaringTypeName();
            // 方法名
            log.debug("请求路径: {}, 方法名: {}", methodPackage, methodSignature.getMethod().getName());
            result = joinPoint.proceed();
        } finally {
            log.debug("请求耗时: {}ms", System.currentTimeMillis() - startTime);
        }
        return result;
    }


    /**
     * 打印请求URL
     */
    private void requestURI() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestURL = request.getRequestURI();
        log.debug("请求URL：{}", requestURL);
    }

}