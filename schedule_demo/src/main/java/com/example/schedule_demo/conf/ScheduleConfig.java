package com.example.schedule_demo.conf;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

//@Configuration
//public class ScheduleConfig implements SchedulingConfigurer {
//
//    public static final int cpuNum = Runtime.getRuntime().availableProcessors();
//
//    private static final int corePoolSize = cpuNum;
//
//    private static final String threadNamePrefix = "scheduled-task-%d";
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.setScheduler(new ScheduledThreadPoolExecutor(corePoolSize,
//                new BasicThreadFactory.Builder().namingPattern(threadNamePrefix).daemon(true).build(),
//                new ThreadPoolExecutor.CallerRunsPolicy()));
//    }
//}

