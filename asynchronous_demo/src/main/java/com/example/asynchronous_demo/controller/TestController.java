package com.example.asynchronous_demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {


    @GetMapping("/getDeferredResult")
    private DeferredResult<String> getDeferredResult() {
        long timeOutInMilliSec = 1000 * 10L;
        String timeOutResp = "超时响应";

        DeferredResult<String> deferredResult = new DeferredResult<>(timeOutInMilliSec, timeOutResp);

        CompletableFuture.runAsync(() -> {
            try {
                // Long polling task; if task is not completed within 10s, timeout response returned for this request
                TimeUnit.SECONDS.sleep(9);
                log.info("执行异步任务");
                // set result after completing task to return response to client
                deferredResult.setResult("任务完成");
            } catch (Exception ignored) {
            }
        });
        return deferredResult;
    }
}
