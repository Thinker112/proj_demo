package org.example.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.io.IOException;
import java.time.Duration;

public class FluxDemo {

    public static void main(String[] args) {
        //        Flux.concat(Flux.just(1,2,3),Flux.just(7,8,9))
//                .subscribe(System.out::println);

        Flux.range(1, 7)
                .log() //日志   onNext(1~7)
                .filter(i -> i > 3) //挑出>3的元素
//                .log() //onNext(4~7)
                .map(i -> "haha-" + i)
//                .log()  // onNext(haha-4 ~ 7)
                .subscribe(System.out::println);
//        [ INFO] (main) | request(unbounded) 请求上游的全部数据
    }

    /**
     * 信号： 正常/异常（取消）<br>
     * SignalType：<br>
     * SUBSCRIBE： 被订阅<br>
     * REQUEST：  请求了N个元素<br>
     * CANCEL： 流被取消<br>
     * ON_SUBSCRIBE：在订阅时候<br>
     * ON_NEXT： 在元素到达<br>
     * ON_ERROR： 在流错误<br>
     * ON_COMPLETE：在流正常完成时<br>
     * AFTER_TERMINATE：中断以后<br>
     * CURRENT_CONTEXT：当前上下文<br>
     * ON_CONTEXT：感知上下文<br>
     * <p>
     * doOnXxx API触发时机<br>
     * 1、doOnNext：每个数据（流的数据）到达的时候触发<br>
     * 2、doOnEach：每个元素（流的数据和信号）到达的时候触发<br>
     * 3、doOnRequest： 消费者请求流元素的时候<br>
     * 4、doOnError：流发生错误<br>
     * 5、doOnSubscribe: 流被订阅的时候<br>
     * 6、doOnTerminate： 发送取消/异常信号中断了流<br>
     * 7、doOnCancle： 流被取消<br>
     * 8、doOnDiscard：流中元素被忽略的时候<br>
     *
     * @param args
     */
    public static void main04(String[] args) {
        // 关键：doOnNext：表示流中某个元素到达以后触发我一个回调
        // doOnXxx要感知某个流的事件，写在这个流的后面，新流的前面
        Flux.just(1, 2, 3, 4, 5, 6, 7, 0, 5, 6)
                .doOnNext(integer -> System.out.println("元素到达：" + integer)) //元素到达得到时候触发
                .doOnEach(integerSignal -> { //doOnEach相比doOnNext封装的详细(包含信号)
                    System.out.println("doOnEach.." + integerSignal);
                })//1,2,3,4,5,6,7,0
                .map(integer -> 10 / integer) //10,5,3,
                .doOnError(throwable -> System.out.println("数据库已经保存了异常：" + throwable.getMessage()))
                .map(integer -> 100 / integer)
                .doOnNext(integer -> System.out.println("元素到哈：" + integer))
                .subscribe(System.out::println);
    }

    // 事件感知API：当流发生什么事的时候，触发一个回调,系统调用提前定义好的钩子函数（Hook【钩子函数】）；doOnXxx；
    public static void main03(String[] args) throws IOException {
        Flux<Integer> flux = Flux.range(1, 7)
                .delayElements(Duration.ofSeconds(1))
                .doOnComplete(() -> System.out.println("流正常结束..."))
                .doOnCancel(() -> System.out.println("流已被取消..."))
                .doOnError(throwable -> System.out.println("流出错..." + throwable))
                .doOnNext(integer -> System.out.println("doOnNext..." + integer));

        flux.subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("订阅者和发布者绑定好了：" + subscription);
//                request(1); //背压， 请求一个元素
                requestUnbounded();//请求无限元素
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("元素到达：" + value);
                if (value < 5) {
                    request(1);
                    if (value == 3) {
                        int i = 10 / 0;
                    }
                } else {
                    cancel();//取消订阅
                }
                //继续要元素
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("数据流结束");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                System.out.println("数据流异常");
            }

            @Override
            protected void hookOnCancel() {
                System.out.println("数据流被取消");
            }

            @Override
            protected void hookFinally(SignalType type) {
                System.out.println("结束信号：" + type);
                // 正常、异常
//                try {
//                    //业务
//                }catch (Exception e){
//
//                }finally {
//                    //结束
//                }
            }
        });

        System.in.read();
    }

    public static void main02(String[] args) {
        Flux<Object> empty = Flux.empty()
                .doOnComplete(() -> System.out.println("work done"));// 空流，此时代表完成信号。

        empty.subscribe();
    }

    public static void main01(String[] args) throws IOException {
        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5);

        just.subscribe(System.out::println);

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        interval.subscribe(System.out::println);

        System.in.read();
    }
}
