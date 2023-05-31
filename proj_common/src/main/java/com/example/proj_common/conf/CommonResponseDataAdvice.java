package com.example.proj_common.conf;

import com.example.proj_common.annotation.CosmoController;
import com.example.proj_common.annotation.IgnoreCosmoResult;
import com.example.proj_common.domain.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.util.Objects;

@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 标注了@CosmoController，且类及方法上都没有标注@IgnoreCosmoResult的方法才进行包装
        return methodParameter.getDeclaringClass().isAnnotationPresent(CosmoController.class)
                && !methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreCosmoResult.class)
                && !Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(IgnoreCosmoResult.class);
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        // 已经包装过的，不再重复包装
        if (body instanceof Result) {
            return body;
        }
        //String 需要特殊处理
        if (body instanceof String){
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(Result.success(body));
        }

        return Result.success(body);
    }
}