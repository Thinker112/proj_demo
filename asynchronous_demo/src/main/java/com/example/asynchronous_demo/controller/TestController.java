package com.example.asynchronous_demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class TestController {

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

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

    @GetMapping("/stream")
    public SseEmitter streamLogs() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));

        return emitter;
    }

    public void sendLogMessage(String message) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().data(message));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }

//    public static void main(String[] args) {
//
//        ScheduledExecutorService executorService =
//                Executors.newScheduledThreadPool(1);
//
//        int i = 0;
//
//        while (true){
//
//            i++;
//            if (i == 1000){
//                break;
//            }
//
//            final int[] i2 = { i };
//
//            executorService.schedule(() -> {
//                String name = Thread.currentThread().getName();
//                System.out.println( name + "_ i = " +  i2[0]);
//            }, 1000L * i, TimeUnit.MILLISECONDS);
//
//        }
//
//        System.out.println("主线程执行。。。");
//    }
}
