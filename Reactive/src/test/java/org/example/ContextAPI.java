package org.example;

import org.junit.Test;
import reactor.core.publisher.Flux;

/**
 * @description: ContextAPI
 */
public class ContextAPI {

    @Test
    public void test01() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .transformDeferredContextual((flux, context) -> {
                    System.out.println("flux = " + flux);
                    System.out.println("context = " + context);
                    return flux.map(i -> i + "-->" + context.get("key"));
                })
                //上游能拿到下游最近的一次数据
                .contextWrite(context -> context.put("key", "value"))
                .subscribe(v -> System.out.println("v = " + v));

        //命令式编程： controller -> service -> dao
        //响应式编程： dao(数据源) -> service -> controller ; 从下游反向传播
    }
}
