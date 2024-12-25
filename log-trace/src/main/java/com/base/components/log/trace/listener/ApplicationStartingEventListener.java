package com.base.components.log.trace.listener;

import org.slf4j.MDC;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

import java.util.UUID;

import static com.base.components.log.trace.constant.TraceConstant.TRACE_ID;

/**
 * 参照：LoggingApplicationListener
 *
 * @author xianfeng
 * @date 2023/6/21 16:17
 */
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent>, Ordered {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        MDC.put(TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
