package AsyncMethodInvocation;

import java.util.concurrent.ExecutionException;

/**
 * AsyncResult（用于异步评估值的中间容器）
 * @param <T>
 */
public interface AsyncResult<T> {
    boolean isCompleted();
    T getValue() throws ExecutionException;
    void await() throws InterruptedException;
}
