package com.base.components.log.trace.async;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.UUID;

import static com.base.components.log.trace.constant.TraceConstant.TRACE_ID;

public class TraceTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {

        try {
            Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
            return () -> {
                try {
                    if (CollectionUtils.isEmpty(copyOfContextMap)) {
                        MDC.put(TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
                    } else {
                        MDC.setContextMap(copyOfContextMap);
                    }
                    runnable.run();
                } finally {
                    MDC.clear();
                }
            };
        } catch (IllegalStateException e) {
            return runnable;
        }
    }
}
