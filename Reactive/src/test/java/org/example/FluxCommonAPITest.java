package org.example;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FluxCommonAPITest {

    @Test
    public void parallelFlux() throws IOException {
        Flux.range(1, 10000)
                .buffer(100)
                .parallel(8)
                .runOn(Schedulers.newParallel("my-thread"))
                .log()
                .flatMap(Flux::fromIterable)
                .subscribe(v -> System.out.println("v = " + v));

        System.in.read();
    }

    @Test
    public void filter() {
        Flux.range(1, 10)
//                .log()
                .filter(i -> i % 2 == 0)//过滤偶数
                .log()
                .subscribe();
    }

    @Test
    public void flatMap() {
        Flux.just("zhang san", "li si")
                .flatMap(str -> {
                    String[] split = str.split(" ");
                    return Flux.fromArray(split);
                })
                .log()
                .subscribe();
    }

    @Test
    public void concat() {
//        Flux.concat(Flux.just(1, 2, 3), Flux.just(4, 5, 6), Flux.just("a", "b", "c"))
//                .log()
//                .subscribe();

//        Flux.just(1, 2, 3)
//                .concatWith(Flux.just(4, 5, 6))//concatWith只能与上游的流元素类型一致
//                .log()
//                .subscribe();

        Flux.just(1, 2, 3)
                .concatMap(number -> Flux.just(number + " -> a", 4, 5))
                .log()
                .subscribe();
    }

    @Test
    public void transform() {
//        Flux.just("a", "b", "c")
//                .transform(s -> s.map(String::toUpperCase))
//                .log()
//                .subscribe();
        AtomicInteger integer = new AtomicInteger(0);

        Flux<String> transform = Flux.just("a", "b", "c")
//                .transform(values -> { //不会共享外部变量的值（无状态转换）
                .transformDeferred(values -> {//共享外部变量的值 （有状态转换）
                    if (integer.incrementAndGet() == 1) {// ++integer
                        //第一次调用, 所有元素都转大写
                        return values.map(String::toUpperCase);
                    } else {
                        return values;
                    }
                });

        transform.subscribe(v -> System.out.println("订阅者1： v = " + v));
        transform.subscribe(v -> System.out.println("订阅者2： v = " + v));
    }

    @Test
    public void empty() {
        //Mono.jsut(null) //流里面有一个null值元素
        Mono.empty() //没有元素，只有完成信号/结束信号
                .defaultIfEmpty("x")
                .log()
                .subscribe();

        Mono.empty() //没有元素，只有完成信号/结束信号
                .switchIfEmpty(Mono.just("haha"))
                .log()
                .subscribe();
    }

    @Test
    public void merge() throws IOException {
//        Flux.merge(
//                        Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1)),
//                        Flux.just("haha", "xixi").delayElements(Duration.ofMillis(400)),
//                        Flux.just("heihei").delayElements(Duration.ofSeconds(2)))
//                .log()
//                .subscribe();

        Flux.mergeSequential(
                        Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1)),
                        Flux.just("haha", "xixi").delayElements(Duration.ofMillis(400)),
                        Flux.just("heihei").delayElements(Duration.ofSeconds(2)))
                .log()
                .subscribe();

        System.in.read();

    }

    @Test
    public void zip() {
        Flux.just(1, 2, 3, 4, 5)
//                .zipWith(Flux.just("a", "b", "c", "d", "e"))
//                .map(tuple -> tuple.getT1() + tuple.getT2())
                //2元组
                .zipWith(Flux.just("a", "b", "c", "d", "e"), (number, letter) -> number + letter)
                .log()
                .subscribe();

        Flux.zip(
                        Flux.just(1, 2),
                        Flux.just("a", "b"),
                        Flux.just(6, 7),
                        Flux.just("d", "e"))
                //4元组
                .map(tuple -> tuple.getT1() + tuple.getT2() + tuple.getT3() + tuple.getT4())
                .log()
                .subscribe();
    }

    @Test
    public void retryAndTimeout() throws IOException {
        Flux.just(1)
                .delayElements(Duration.ofSeconds(3))
                .log()
                .timeout(Duration.ofSeconds(1))
                .retry(2)// 把流重头到尾重新请求一次
                .onErrorReturn(3)
                .map(i ->  "haha" + i)
                .log()
                .subscribe();

        System.in.read();
    }

    @Test
    public void block() {
        Integer integer = Flux.range(1, 4)
                .map(i -> i * 2)
//                .blockFirst();
                .blockLast();
        System.out.println("integer = " + integer);

        List<Integer> integer2 = Flux.range(1, 4)
                .map(i -> i * 2)
                .collectList()
                .block();
        System.out.println("integer2 = " + integer2);
    }
}
