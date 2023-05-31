package AsyncMethodInvocation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * AsyncExecutor（用于管理异步任务的执行）
 */
public interface AsyncExecutor {

    <T> AsyncResult<T> startProcess(Callable<T> task);

    <T> AsyncResult<T> startProcess(Callable<T> task, AsyncCallback<T> callback);

    <T> T endProcess(AsyncResult<T> asyncResult) throws ExecutionException, InterruptedException;
}
