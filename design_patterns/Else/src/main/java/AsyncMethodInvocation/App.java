package AsyncMethodInvocation;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * 异步方法调用
 */
@Slf4j
public class App {

    private static final String ROCKET_LAUNCH_LOG_PATTERN = "Space rocket <%s> launched successfully";

    public static void main(String[] args) throws Exception {
        // 构造一个将执行异步任务的新执行程序
        var executor = new ThreadAsyncExecutor();

        // 以不同的处理时间开始一些异步任务，最后两个使用回调处理程序
        final var asyncResult1 = executor.startProcess(lazyval(10, 500));
        final var asyncResult2 = executor.startProcess(lazyval("test", 300));
        final var asyncResult3 = executor.startProcess(lazyval(50L, 700));
        final var asyncResult4 = executor.startProcess(lazyval(20, 400), callback("Deploying lunar rover"));
        final var asyncResult5 =
                executor.startProcess(lazyval("callback", 600), callback("Deploying lunar rover"));

        // 在当前线程中模拟异步任务正在它们自己的线程中执行
        Thread.sleep(350); // 哦，兄弟，我们在这很辛苦
        log("Mission command is sipping coffee");

        // 等待任务完成
        final var result1 = executor.endProcess(asyncResult1);
        final var result2 = executor.endProcess(asyncResult2);
        final var result3 = executor.endProcess(asyncResult3);
        asyncResult4.await();
        asyncResult5.await();

        // log the results of the tasks, callbacks log immediately when complete
        // 记录任务结果的日志， 回调的日志会在回调完成时立刻记录
        log("Space rocket <" + result1 + "> launch complete");
        log("Space rocket <" + result2 + "> launch complete");
        log("Space rocket <" + result3 + "> launch complete");
    }

    /**
     * Creates a callable that lazily evaluates to given value with artificial delay.
     *
     * @param value       value to evaluate
     * @param delayMillis artificial delay in milliseconds
     * @return new callable for lazy evaluation
     */
    private static <T> Callable<T> lazyval(T value, long delayMillis) {
        return () -> {
            Thread.sleep(delayMillis);
            log(String.format(ROCKET_LAUNCH_LOG_PATTERN, value));
            return value;
        };
    }

    /**
     * Creates a simple callback that logs the complete status of the async result.
     *
     * @param name callback name
     * @return new async callback
     */
    private static <T> AsyncCallback<T> callback(String name) {
        return (value, ex) -> {
            if (ex.isPresent()) {
                log(name + " failed: " + ex.map(Exception::getMessage).orElse(""));
            } else {
                log(name + " <" + value + ">");
            }
        };
    }

    private static void log(String msg) {
        log.info(msg);
    }
}
