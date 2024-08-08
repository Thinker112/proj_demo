package com.example.demo;

import io.github.openfeign.querydsl.jpa.spring.repository.config.EnableQuerydslRepositories;
import io.github.openfeign.querydsl.jpa.spring.repository.support.QuerydslJpaRepositoryFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;

@Slf4j
@EnableScheduling
@EnableAspectJAutoProxy
@EnableQuerydslRepositories
@SpringBootApplication
@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.repository",
        repositoryFactoryBeanClass = QuerydslJpaRepositoryFactoryBean.class
)
@EnableTransactionManagement
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
