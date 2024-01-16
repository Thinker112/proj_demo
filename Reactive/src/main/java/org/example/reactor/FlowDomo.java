package org.example.reactor;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class FlowDomo {

    public static void main(String[] args) {
        //发布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        MyProcessor myProcessor = new MyProcessor();//中间操作
        MyProcessor myProcessor2 = new MyProcessor();

        //订阅者
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {

            private Flow.Subscription subscription;//维护发布者与订阅者的绑定关系

            @Override
            public void onSubscribe(Flow.Subscription Subscription) {
                System.out.println(Thread.currentThread() + "  订阅开始了  " + Subscription);

                this.subscription = Subscription;

                //向上游发送一个信号，表示当前订阅者已经准备好接收一个消息
                Subscription.request(1);

//                Subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String item) {
                System.out.println(Thread.currentThread() + " item = " + item);
                subscription.request(1);
                if ("publish -> 5".equals(item)) {
                    subscription.cancel();//发送cancel信号, publish -> 5 后面的数据不要了
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("出现错误: " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("发布完成");
            }
        };
        //发布者 -> MyProcessor <- 订阅者
        publisher.subscribe(myProcessor);
        myProcessor.subscribe(myProcessor2);
        myProcessor2.subscribe(subscriber);

        for (int i = 0; i < 10; i++) {
            publisher.submit("publish -> " + i);//发布10条数据到缓冲区
        }

        publisher.close();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //自定义一个中间操作
    static class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<String, String> {

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println("MyProcessor订阅绑定完成");
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(String item) {
            item = "MyProcessor_" + item;
            System.out.println("item = " + item);
            this.submit(item);//加工后的数据发布出去
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {

        }
    }
}


