package com.example.proj_common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaRepositories
public class ProjCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjCommonApplication.class, args);
    }

}
