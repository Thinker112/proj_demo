package com.example.asynchronous_demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class LogMessageGenerator {

    private final TestController  controller;


    @Scheduled(fixedRate = 2000) // 每2秒生成一条日志消息
    public void generateLogMessage() {
        String logMessage = "This is a log message at " + System.currentTimeMillis();
        controller.sendLogMessage(logMessage);
    }
}
