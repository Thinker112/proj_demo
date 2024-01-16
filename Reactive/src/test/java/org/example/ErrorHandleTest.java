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
//                .log()
                .map(i -> "100 / " + i + " = " + (100 / i)) //this triggers an error with 0
                .log()
//                .onErrorReturn("Divided by zero :(");// error handling example
                .onErrorComplete();//将错误信号替换为完成信号
        flux.log().subscribe();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
