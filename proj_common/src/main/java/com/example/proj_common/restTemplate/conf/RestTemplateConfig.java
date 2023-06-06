package com.example.proj_common.restTemplate.conf;

import com.example.proj_common.restTemplate.interceptor.CustomClientHttpRequestInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder
                //设置连接超时时间
                .setConnectTimeout(Duration.ofSeconds(5000))
                //设置读取超时时间
                .setReadTimeout(Duration.ofSeconds(5000))
                //设置根路径
                .rootUri("http://192.168.78.128:8080/api_jsonrpc.php")
                //添加拦截器
                .additionalInterceptors(new CustomClientHttpRequestInterceptor())
                //构建
                .build();
    }
}
