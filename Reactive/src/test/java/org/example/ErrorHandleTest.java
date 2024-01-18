package org.example;

import org.junit.Test;
import reactor.core.publisher.Flux;

/**
 * any error in a reactive sequence is a terminal event.
 * Even if an error-handling operator is used, it does not let the original sequence continue.
 * Rather, it converts the onError signal into the start of a new sequence (the fallback one).
 * In other words, it replaces the terminated sequence upstream of it.
 */
public class ErrorHandleTest {

    @Test
    public void errorHandleTest(){
        Flux<String> flux = Flux.just(1, 2, 0)
                .map(i -> "100 / " + i + " = " + (100 / i)) //this triggers an error with 0
//                .log()
//                .onErrorResume(err -> Mono.just("resume :)"));
                .onErrorMap(err -> new RuntimeException("map -> pam"));//重新抛出自定义异常
//                .log();
//                .onErrorReturn(NullPointerException.class, "空指针异常");
//                .onErrorReturn("Divided by zero :(");// error handling example
        flux.log().subscribe();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void doOnError() {
        Flux.just(1, 2, 0, 3, 4, 5)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .doOnError(err -> System.out.println("onError已被记录: " + err))
                .doFinally(signal -> System.out.println("signal = " + signal))
                .subscribe(v -> System.out.println("v = " + v),
                        err -> System.out.println("onError被处理: " + err),
                        () -> System.out.println("流完成信号"));
    }

    @Test
    public void onErrorContinue() {
        Flux.just(1, 2, 0, 3, 4, 5)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .onErrorContinue((err, o) -> System.out.println("onErrorContinue被记录: " + err + " " + o + " -> 继续执行"))
                .subscribe(v -> System.out.println("v = " + v),
                        err -> System.out.println("onError被处理: " + err),
                        () -> System.out.println("流完成信号"));
    }

    @Test
    public void onErrorComplete() {
        Flux.just(1, 2, 0, 3, 4, 5)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .onErrorComplete()//将错误信号替换为完成信号
                .subscribe(v -> System.out.println("v = " + v),
                        err -> System.out.println("onError被处理: " + err),
                        () -> System.out.println("流完成信号"));
    }

    @Test
    public void onErrorStop() {
        Flux.just(1, 2, 0, 3, 4, 5)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .onErrorStop()
                .subscribe(v -> System.out.println("v = " + v),
                        err -> System.out.println("onError被处理: " + err),
                        () -> System.out.println("流完成信号"));
            }
}
