package org.example;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Sinks: 接收器， 数据管道 <br>
 * Sinks.many() 发送Flux数据<br>
 * Sinks.one() 发送Mono数据<br>
 * Sinks.many().unicast() 单播<br>
 * Sinks.many().multicast() 多播<br>
 * Sinks.many().replay() 重放<br>
 */
public class SinksTest {

    @Test
    public void unicast() throws IOException {
        Sinks.Many<Object> many = Sinks.many()
                .unicast()  // 单播
                .onBackpressureBuffer(new LinkedBlockingDeque<>()); //背压队列

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                many.tryEmitNext(i + "-> a");
            }
        }).start();

        //订阅
        many.asFlux().subscribe(System.out::println);

        System.in.read();
    }

    @Test
    public void multicast() throws IOException {
        Sinks.Many<Object> many = Sinks.many()
                .multicast()  // 单播
                .onBackpressureBuffer(); //背压队列

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                many.tryEmitNext(i + "-> a");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        //订阅
        many.asFlux().subscribe(v1 -> System.out.println("v1: " + v1));
        //默认订阅者会从订阅的那一刻接收元素
        many.asFlux().subscribe(v2 -> System.out.println("v2: " + v2));

        System.in.read();
    }

    @Test
    public void replay() throws IOException {
        Sinks.Many<Object> many = Sinks.many()
                .replay()
                .limit(3);
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                many.tryEmitNext(i + "-> a");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        //订阅
        many.asFlux().subscribe(v1 -> System.out.println("v1: " + v1));

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            many.asFlux().subscribe(v2 -> System.out.println("v2: " + v2));
        });

        System.in.read();
    }

    @Test
    public void cache() throws IOException {

        Flux<Integer> cache = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .cache(3);

        cache.subscribe();

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //3秒后订阅
            cache.subscribe(System.out::println);
        });

        System.in.read();
    }
}
