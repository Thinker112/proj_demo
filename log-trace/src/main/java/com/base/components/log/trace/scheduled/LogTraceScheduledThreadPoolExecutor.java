package com.base.components.log.trace.scheduled;

import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;

import java.util.UUID;
import java.util.concurrent.*;

import static com.base.components.log.trace.constant.TraceConstant.TRACE_ID;

/**
 * 对任务进行了包装，增加traceId
 *
 * @author xianfeng
 * @date 2023/12/21 18:32
 * @description:
 */
public class LogTraceScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    public LogTraceScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    public LogTraceScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    public LogTraceScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
    }

    public LogTraceScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, threadFactory, handler);
    }

    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable r, RunnableScheduledFuture<V> task) {
        return new CustomTask<V>(task);
    }

    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Callable<V> callable, RunnableScheduledFuture<V> task) {
        return new CustomTask<V>(task);
    }

    @RequiredArgsConstructor
    public static class CustomTask<T> implements RunnableScheduledFuture<T> {

        private final RunnableScheduledFuture<T> task;

        @Override
        public boolean isPeriodic() {
            return task.isPeriodic();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return task.getDelay(unit);
        }

        @Override
        public int compareTo(Delayed o) {
            return task.compareTo(o);
        }

        @Override
        public void run() {
            try {
                MDC.put(TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
                task.run();
            } finally {
                MDC.clear();
            }
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return task.cancel(mayInterruptIfRunning);
        }

        @Override
        public boolean isCancelled() {
            return task.isCancelled();
        }

        @Override
        public boolean isDone() {
            return task.isDone();
        }

        @Override
        public T get() throws InterruptedException, ExecutionException {
            return task.get();
        }

        @Override
        public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return task.get(timeout, unit);
        }
    }
}
