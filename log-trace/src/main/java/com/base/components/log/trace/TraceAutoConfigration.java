package com.base.components.log.trace;

import com.base.components.log.trace.async.TraceTaskDecorator;
import com.base.components.log.trace.filter.TraceFilter;
import com.base.components.log.trace.log.RequestLog;
import com.base.components.log.trace.quartz.QuartzExecutorWithTraceId;
import com.base.components.log.trace.scheduled.LogTraceThreadPoolTaskScheduler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
public class TraceAutoConfigration {

    @Bean
    @ConditionalOnMissingBean
    public TaskDecorator taskDecorator() {
        return new TraceTaskDecorator();
    }

    @ConditionalOnClass({SchedulerFactoryBean.class})
    @Configuration
    static class QuartzTraceIdConfig {
        @Bean
        public QuartzExecutorWithTraceId quartzExecutorWithTraceId() {
            return new QuartzExecutorWithTraceId();
        }
    }

    @ConditionalOnClass(ThreadPoolTaskScheduler.class)
    @Configuration
    static class ScheduledTraceIdConfig {
        @Bean
        public ThreadPoolTaskScheduler threadPoolTaskScheduler(TaskSchedulerBuilder builder) {
            return builder.configure(new LogTraceThreadPoolTaskScheduler());
        }
    }

    @ConditionalOnClass({FilterRegistrationBean.class, GenericFilterBean.class})
    @Configuration
    static class FilterRegistrationConfig {
        @Bean
        @ConditionalOnMissingBean(name = "traceFilter")
        public FilterRegistrationBean traceFilter() {
            FilterRegistrationBean registration = new FilterRegistrationBean();
            registration.setFilter(new TraceFilter());
            registration.addUrlPatterns("/*");
            registration.setName("traceFilter");
            registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
            return registration;
        }
    }

    @ConditionalOnClass(ProceedingJoinPoint.class)
    @Configuration
    static class AspectjAopConfig {
        @Bean
        public RequestLog requestLog() {
            return new RequestLog();
        }
    }

}
