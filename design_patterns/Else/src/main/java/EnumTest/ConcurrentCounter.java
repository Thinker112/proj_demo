package EnumTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yueyubo
 * @date 2024-06-14
 */
public class ConcurrentCounter {

    private final AtomicInteger counter = new AtomicInteger(0);

    private static Integer notAtomicInt = 0;

    public void increment() {
        counter.incrementAndGet();
    }

    public int getValue() {
        return counter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        atomic();
        notAtomic();
    }

    private static void atomic() throws InterruptedException {
        ConcurrentCounter counter = new ConcurrentCounter();
        // 创建多个线程并发递增计数器
        for (int i = 0; i < 1000; i++) {
            new Thread(counter::increment).start();
        }

        TimeUnit.SECONDS.sleep(2);
        // 等待所有线程完成后，获取计数器的值
        System.out.println("atomic Counter Value: " + counter.getValue());
    }

    /**
     * 假设 notAtomicInt 的初始值为 0，有两个线程 T1 和 T2 同时执行 notAtomicInt++。以下是可能的执行步骤：
     * T1 读取 notAtomicInt，值为 0。
     * T2 读取 notAtomicInt，值也为 0。
     * T1 计算 0 + 1，并将 1 写回 notAtomicInt。
     * T2 计算 0 + 1，并将 1 写回 notAtomicInt。
     */
    private static void notAtomic() throws InterruptedException {
        // 创建多个线程并发递增计数器
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> notAtomicInt++).start();
        }

        TimeUnit.SECONDS.sleep(2);
        // 等待所有线程完成后，获取计数器的值
        System.out.println("not atomic Counter Value: " + notAtomicInt);
    }
}
