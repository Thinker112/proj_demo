package com.example.schedule_demo.schedule;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableAsync
public class ScheduleTask {


    /**
     * `@Scheduled` 注解默认单线程执行 <br>
     *  可以通过配置多线程执行,  如果定时任务处理时间过长会阻塞下一个任务的执行，需要加上`@Async` 注解异步执行
     */
    @SneakyThrows
//    @Async
    @Scheduled(initialDelay = 2000, fixedDelay = 1000)
    void timeTask(){
        log.info("......Task Start [{}].....", Thread.currentThread().getName());
        Thread.sleep(2000);
        log.info(">>>>>>Task End [{}]>>>>>>>", Thread.currentThread().getName());

    }


    //    @Async
    @Scheduled(initialDelay = 4000, fixedDelay = 3000)
    @SneakyThrows
    void timeTask2(){
        log.info("......Task Start2 [{}].....", Thread.currentThread().getName());
        Thread.sleep(3000);
        log.info(">>>>>>Task End2 [{}]>>>>>>>", Thread.currentThread().getName());

    }

}
