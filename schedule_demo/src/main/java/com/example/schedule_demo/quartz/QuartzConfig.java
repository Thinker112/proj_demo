package com.example.schedule_demo.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob")
                .storeDurably()
                .build();
    }

    @Bean
    public SimpleTrigger myTrigger(JobDetail myJobDetail) {
        // 计算从现在到今天凌晨2点的时间差，作为第一次执行的延迟
        long timeUntilFirstRun = calculateTimeUntilFirstRun("16:16");
        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail)
                .withIdentity("myTrigger")
                .startAt(new Date(System.currentTimeMillis() + timeUntilFirstRun))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(24) // 每24小时执行一次
                        .repeatForever())
                .build();
    }

    private long calculateTimeUntilFirstRun(String targetTime) {
        LocalTime localTime = LocalTime.parse(targetTime);
        LocalDateTime now = LocalDateTime.now(); // 当前时间
        LocalDateTime nextRun = now.with(localTime); // 今天的目标时间

        // 如果当前时间已经过了今天的目标时间，则设置下一次执行时间为明天的目标时间
        if (now.isAfter(nextRun)) {
            nextRun = nextRun.plusDays(1);
        }

        // 计算从现在到下一次执行时间的持续时间
        Duration duration = Duration.between(now, nextRun);
        return duration.toMillis(); // 将持续时间转换为毫秒
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(myTrigger(myJobDetail()));
        return schedulerFactoryBean;
    }
}