package com.example.schedule_demo.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

//@Slf4j
//@Configuration
//public class ThreadPoolTaskConfig implements WebMvcConfigurer {
//
//    // 方式2: taskScheduler
//    @Bean("taskScheduler")
//    public Executor taskScheduler() {
//        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//        taskScheduler.setPoolSize(3);
//        taskScheduler.setThreadNamePrefix("schedule-task-");
//        taskScheduler.setRejectedExecutionHandler(
//                new RejectedExecutionHandler() {
//                    /**
//                     * 自定义线程池拒绝策略（模拟发送告警邮件）
//                     */
//                    @Override
//                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                        log.info("发送告警邮件======>:嘿沙雕，线上定时任务卡爆了, 当前线程名称为:{}, 当前线程池队列长度为:{}",
//                                r.toString(),
//                                executor.getQueue().size());
//                    }
//                });
//        taskScheduler.initialize();
//        return taskScheduler;
//    }
//}