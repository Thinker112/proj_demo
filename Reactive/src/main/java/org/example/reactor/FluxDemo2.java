package org.example.reactor;

import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.net.DatagramSocket;
import java.time.Duration;
import java.util.List;

/**
 * https://projectreactor.io/docs/core/release/reference/index.html
 */
public class FluxDemo2 {

    //线程调度
    public static void main(String[] args) throws IOException {

        Flux.range(1, 100)
                .publishOn(Schedulers.immediate())//指定线程调度
                .log()
                .subscribe();

//        Schedulers.immediate();//使用当前线程运行所有操作（默认）
//        Schedulers.single();// 使用一个固定单线程调度
//        Schedulers.boundedElastic(); //有界，弹性调度
    }

    //自定义流中元素处理规则
    public static void main06(String[] args) throws IOException {
        Flux.range(1, 100)
                .handle((value, sink) -> {
                    sink.next("tom: " + value);
                })
                .log()
                .subscribe();
    }

    //disposable
    public static void main05(String[] args) throws IOException {
        Flux<Integer> flux = Flux.range(1, 1000)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> i + 1)
                .log();

        Disposable disposable = flux.subscribe();

        new Thread(() -> {
            try {
                Thread.sleep(3000);
                disposable.dispose();//丢弃
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();

        System.in.read();
    }

    //自定义生成序列(同步）
    public static void main04(String[] args) throws IOException {
        Flux<String> flux = Flux.generate(
                () -> 0,//提供初始状态值 0
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) {//发送10个元素
                        sink.complete();
                    }
                    return state + 1;
                });

        flux.log().subscribe();
    }

    //limit, 限流
    public static void main03(String[] args) {
        Flux.range(1, 1000)
                .log()
                .limitRate(100)//一次预取100一个元素
                .subscribe();

        //预取策略：75%， limitRate(100), 第一次抓取100个元素，如果75个元素已经处理，则继续抓取新的75% 元素
    }

    //buffer 缓冲区， Flux默认 buffer(1)
    public static void main02(String[] args) {
        Flux<List<Integer>> buffer = Flux.range(1, 10)
                .buffer(3);//缓冲3个元素

//        buffer.subscribe(item -> System.out.println("item : " + item));

        buffer.subscribe(new BaseSubscriber<List<Integer>>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
//                super.hookOnSubscribe(subscription);
                request(2);//request(N), 请求N次
            }

            @Override
            protected void hookOnNext(List<Integer> value) {
//                super.hookOnNext(value);
                System.out.println("value = " + value);
            }
        });
    }

    public static void main01(String[] args) {
        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5)
                .map(i -> {
                    System.out.println(i + 1);
//                    if (i == 5){
//                        throw new RuntimeException("抛一个异常");
//                    }
                    return i + 1;
                });

//        just.subscribe();//流被订阅

//        just.subscribe(i -> System.out.println("Consumer_" + i));

//        just.subscribe(i -> System.out.println("Consumer_" + i), throwable -> System.out.println("出现错误：" + throwable.getMessage()));

        just.subscribe(i -> System.out.println("Consumer_" + i),
                throwable -> System.out.println("出现错误：" + throwable.getMessage()),
                () -> System.out.println("流传输完成"));
    }
}
