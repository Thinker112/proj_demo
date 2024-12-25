package com.base.components.log.trace.quartz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.time.Duration;
import java.util.UUID;

import static com.base.components.log.trace.constant.TraceConstant.TRACE_ID;

/**
 * 设置quartz的任务执行器
 *
 * @author xianfeng
 * @date 2023/12/21 14:21
 * @description:
 */
@Slf4j
@RequiredArgsConstructor
public class QuartzExecutorWithTraceId implements SchedulerFactoryBeanCustomizer {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 核心线程数 = CPU核心数 + 1
     */
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    /**
     * 线程池最大线程数 = CPU核心数 * 2 + 1
     */
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    /**
     * 非核心线程闲置时超时1s
     */
    private static final int KEEP_ALIVE = 1;

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
        TaskExecutorBuilder builder = new TaskExecutorBuilder();
        builder = builder.corePoolSize(CORE_POOL_SIZE);
        builder = builder.maxPoolSize(MAXIMUM_POOL_SIZE);
        builder = builder.keepAlive(Duration.ofSeconds(KEEP_ALIVE));
        builder = builder.threadNamePrefix("quartzScheduler_worker-");
        //是否允许核心线程关闭
        builder = builder.allowCoreThreadTimeOut(false);
        builder = builder.queueCapacity(1000);
        //关闭时是否等待任务执行完成
        builder = builder.awaitTermination(true);
        //关闭线程池时等待的超时时间
        builder = builder.awaitTerminationPeriod(Duration.ofSeconds(30));
        builder = builder.taskDecorator(runnable ->
                () -> {
                    try {
                        MDC.put(TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
                        runnable.run();
                    } finally {
                        MDC.clear();
                    }
                });
        /**
         * {@link ThreadPoolTaskExecutor}
         */
        ThreadPoolTaskExecutor threadPoolTaskExecutor = builder.build();
        //初始化threadPoolTaskExecutor，不是bean，所以spring不会帮我们调用
        threadPoolTaskExecutor.afterPropertiesSet();
        schedulerFactoryBean.setTaskExecutor(threadPoolTaskExecutor);
    }
}
