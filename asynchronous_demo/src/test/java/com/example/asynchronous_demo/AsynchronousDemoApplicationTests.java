package com.example.asynchronous_demo;

import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

//@SpringBootTest
class AsynchronousDemoApplicationTests {

    @Test
    void contextLoads() {
        //supplyAsync() 表示执行一个异步方法，而 thenApply() 表示执行成功后再串联另外一个异步方法，最后是 thenAccept() 来处理最终结果
//        CompletableFuture.supplyAsync(this::findReceiver)
//                .thenApply(this::sendMsg)
//                .thenAccept(this::notify);


    }

    @Test
    void test01(){
        String result = CompletableFuture
                .supplyAsync(() -> "hello")
                .thenCombine(CompletableFuture.supplyAsync(() -> "world"),
                        (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);
    }

    void test02(){
//        CompletableFuture.supplyAsync(Integer::parseInt) // 输入: "ILLEGAL"
//                .thenApply(r -> r * 2 * Math.PI)
//                .thenApply(s -> "apply>> " + s)
//                .handle((result, ex) -> {
//                    if (result != null) {
//                        return result;
//                    } else {
//                        return "Error handling: " + ex.getMessage();
//                    }
//                });
    }

}
