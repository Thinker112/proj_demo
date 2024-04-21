package org.example.reactor;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @Author yyb <br>
 * @Create 2024-04-15
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        // 创建并启动5个线程，每个线程输出变量i的值
        for (int j = 0; j < 20; j++) {
            new Thread(new Worker(new Random().nextInt(10))).start();
        }

        Thread.sleep(100000); // 主线程等待，以便观察输出
    }

    // 定义一个实现Runnable接口的Worker类
    static class Worker implements Runnable {
        private int value;

        public Worker(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            System.out.println("Thread " + Thread.currentThread().getId() + ": " + value);
        }
    }
}
