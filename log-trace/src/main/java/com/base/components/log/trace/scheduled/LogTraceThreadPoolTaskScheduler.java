package com.base.components.log.trace.scheduled;

import com.base.components.log.trace.scheduled.LogTraceScheduledThreadPoolExecutor;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;

/**
 * 将jdk的ScheduledExecutorService替换为增加日志输出的ScheduledExecutorService
 *
 * @author xianfeng
 * @date 2023/12/21 18:58
 * @description:
 */
public class LogTraceThreadPoolTaskScheduler extends ThreadPoolTaskScheduler {
    @Override
    protected ScheduledExecutorService createExecutor(int poolSize, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        return new LogTraceScheduledThreadPoolExecutor(poolSize, threadFactory, rejectedExecutionHandler);
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable task, Trigger trigger) {
        return super.schedule(task, trigger);
    }
}
