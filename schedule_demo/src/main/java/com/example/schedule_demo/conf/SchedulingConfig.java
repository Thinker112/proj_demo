package com.example.schedule_demo.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

//@Slf4j
//@Component
//public class SchedulingConfig implements SchedulingConfigurer {
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//        taskScheduler.setPoolSize(10);
//        taskScheduler.setThreadNamePrefix("custom--task-");
//        taskScheduler.setRejectedExecutionHandler(
//                new RejectedExecutionHandler() {
//                    /**
//                     * 自定义线程池拒绝策略（模拟发送告警邮件）
//                     */
//                    @Override
//                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                        log.info("发送告警邮件======>:定时任务卡爆了, 当前线程名称为:{}, 当前线程池队列长度为:{}",
//                                r.toString(),
//                                executor.getQueue().size());
//                    }
//                });
//        taskScheduler.initialize();
//        taskRegistrar.setScheduler(taskScheduler);
//    }
//}
