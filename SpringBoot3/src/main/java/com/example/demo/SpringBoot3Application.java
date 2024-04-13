package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBoot3Application {

    public static void main(String[] args) {
        //关闭热部署
        System.setProperty("spring.devtools.restart.enabled", "false");

//        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBoot3Application.class, args);
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        log.info("已加载的Bean数量 : {}", beanDefinitionNames.length);
//        for (String beanDefinitionName : beanDefinitionNames) {
//            log.info("beanDefinitionName : {}", beanDefinitionName);
//        }
        new SpringApplicationBuilder()
                .main(SpringBoot3Application.class)
                .sources(SpringBoot3Application.class)
//                .bannerMode(Banner.Mode.OFF)
                .run(args);

        log.info("##### SpringBoot3启动成功 :) #####");
    }
}
