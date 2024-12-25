package com.base.components.log.trace.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

import static com.base.components.log.trace.constant.TraceConstant.TRACE_ID;

@Aspect
public class ScheduledAspect {

    private static final Logger log = LoggerFactory.getLogger(ScheduledAspect.class);

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void schedulePointcut() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicPointcut() {
    }


    @Pointcut("schedulePointcut() && publicPointcut()")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        MDC.put(TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("",e);
            throw e;
        } finally {
            MDC.clear();
        }
        return result;
    }
}
