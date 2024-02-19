package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBoot3Application {

    public static void main(String[] args) {
        //关闭热部署
        System.setProperty("spring.devtools.restart.enabled", "false");

        SpringApplication.run(SpringBoot3Application.class, args);
        log.info("Application Launched Successfully :)");
    }

}
