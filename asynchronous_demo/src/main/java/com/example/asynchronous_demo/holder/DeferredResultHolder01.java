package com.example.asynchronous_demo.holder;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 在使用 DeferredResult 时，为了避免出现多个线程同时设置 DeferredResult 结果的情况，
 * 一般需要使用 DeferredResultHolder 对象来持有 DeferredResult，并在需要设置结果时通过 DeferredResultHolder 来进行操作。
 * 具体来说，DeferredResultHolder 一般是在 Controller 中使用的，它可以将 DeferredResult 对象作为 Controller 方法的返回值返回给客户端。
 * 当需要设置 DeferredResult 的结果时，可以将 DeferredResultHolder 对象注入到需要异步处理的方法中，
 * 并在异步处理完成后通过 DeferredResultHolder 来设置 DeferredResult 的结果。
 * 使用 DeferredResultHolder 可以有效避免多个线程同时设置 DeferredResult 结果的问题，从而保证 DeferredResult 的正确性和可靠性。
 * @param <T>
 */
@Component
public class DeferredResultHolder01<T> {

    private DeferredResult<T> deferredResult;

    public synchronized DeferredResult<T> getDeferredResult() {
        if (deferredResult == null || deferredResult.isSetOrExpired()) {
            deferredResult = new DeferredResult<>();
        }
        return deferredResult;
    }

    public synchronized void setResult(T result) {
        if (deferredResult != null && !deferredResult.isSetOrExpired()) {
            deferredResult.setResult(result);
        }
    }
}
