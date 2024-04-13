package com.example.demo.listener;

import lombok.extern.java.Log;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Duration;

@Log
public class LifecycleListener implements SpringApplicationRunListener {

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        //不能用日志输出，此时日志配置还未加载
        System.out.println("应用程序正在启动...");
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("应用程序启动成功");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("应用程序启动失败...");
    }
}
