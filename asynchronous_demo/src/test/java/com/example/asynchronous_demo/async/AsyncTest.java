package com.example.asynchronous_demo.async;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author yueyubo
 * @date 2024-06-27
 */
public class AsyncTest {

    @Test
    public void testFuture() {
        // 创建一个CompletableFuture对象
        CompletableFuture<String> future = new CompletableFuture<>();

        // 注册一个回调，当Future完成时执行
        future.thenAccept(result -> {
            System.out.println("Future完成，结果是：" + result + Thread.currentThread().getName());
        });

        // 在另一个线程中完成Future
        new Thread(() -> {
            try {
                // 模拟一些耗时操作
                Thread.sleep(2000);
                // 完成Future并设置结果
                future.complete("操作成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 主线程继续执行其他操作
        System.out.println("主线程继续执行其他操作" + Thread.currentThread().getName());

        // 等待Future完成并获取结果（如果需要）
        try {
            String result = future.get();
            System.out.println("最终结果是：" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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
