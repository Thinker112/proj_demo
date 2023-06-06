package com.example.proj_common.restTemplate.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        //打印请求明细
        logRequestDetails(request,body);
        ClientHttpResponse response = execution.execute(request, body);
        //打印响应明细
        logResponseDetails(response);

        return response;
    }

    private void logRequestDetails(HttpRequest request, byte[] body){
        log.info("Headers:{}",request.getHeaders());
        log.info("body:{}",new String(body, StandardCharsets.UTF_8));
        log.info("{}:{}",request.getMethod(),request.getMethodValue());
    }

    private void logResponseDetails(ClientHttpResponse response) throws IOException {
        log.info("Status code : {}",response.getStatusCode());
        log.info("Status text : {}",response.getStatusText());
        log.info("Headers : {}",response.getHeaders());
        //TODO 请求和响应的流只会被读取一次，这里读取了response后返回的response就为 null了
        if (!response.getStatusCode().is2xxSuccessful()){
            log.info("Response body: {}", StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
        }
    }

}
