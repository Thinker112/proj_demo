package com.example.asynchronous_demo.async;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * Future的缺陷:
 * 1. 在没有阻塞的情况下，无法对Future的结果执行进一步的操作。 Future不会告知你它什么时候完
 * 成，你如果想要得到结果，必须通过一个get()方法，该方法会阻塞直到结果可用为止。它不具备将
 * 回调函数附加到Future后并在Future的结果可用时自动调用回调的能力。<br>
 * 2. 无法解决任务相互依赖的问题。 filterWordFuture和newsFuture的结果不能自动发送给
 * replaceFuture, 需要在replaceFuture中手动获取，所以使用Future不能轻而易举地创建异步工作
 * 流。<br>
 * 3. 不能将多个Future合并在一起。 假设你有多种不同的Future, 你想在它们全部并行完成后然后运行
 * 某个函数，Future很难独立完成这一需要。<br>
 * 4. 没有异常处理。 Future提供的方法中没有专门的API应对异常处理，还需要开发者自己手动异常处
 * 理。<br>
 * @author yueyubo
 * @date 2024-07-03
 */
public class FutureDemo {
    static String path = "D:\\project_code\\java\\myself_demo_all\\asynchronous_demo\\src\\test\\java\\com\\example\\asynchronous_demo\\async\\";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // step 1: 读取敏感词汇 => thread1
        Future<String[]> filterWordFuture = executor.submit(() -> {
            String str = CommonUtils.readFile(path + "filter_words.txt");
            for (String s : str.split(",")) {
                System.out.println("sensitive words = " + s);
            }

            return str.split(",");
        });
        // step 2: 读取新闻稿 => thread2
        Future<String> newsFuture = executor.submit(() -> CommonUtils.readFile(path + "news.txt"));
        // step 3: 替换操作 => thread3
        Future<String> replaceFuture = executor.submit(() -> {
            String[] words = filterWordFuture.get();
            String news = newsFuture.get();
            for (String word : words) {
                if (news.contains(word)) {
                    news = news.replace(word, "**");
                }
            }
            return news;
        });
        // step 4: 打印输出替换后的新闻稿 => main
        String filteredNews = replaceFuture.get();
        System.out.println("filteredNews = " + filteredNews);
        executor.shutdown();

        TimeUnit.SECONDS.sleep(3);
    }

    @Test
    @SneakyThrows
    public void supplyAsyncTest() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->
                CommonUtils.readFile(path + "news.txt"));

        String str = future.get();//阻塞并等待newsFuture完成
        System.out.println("str = " + str);
    }

    @Test
    @SneakyThrows
    public void thenApplyTest() {
        CompletableFuture<String[]> apply = CompletableFuture.supplyAsync(() -> CommonUtils.readFile(path + "filter_words.txt"))
                .thenApply(sensitiveStr -> {
                    CommonUtils.printTheadLog("sensitiveStr = " + sensitiveStr);
                    return sensitiveStr.split(",");
                });
        String[] sensitiveStr = apply.get();
        System.out.println("Arrays.toString(filterWords) = " +
                Arrays.toString(sensitiveStr));
        CommonUtils.printTheadLog("main end");

    }

    @Test
    @SneakyThrows
    public void convertSensitiveWordsTest() {
        CompletableFuture.supplyAsync(() -> CommonUtils.readFile(path + "filter_words.txt"))
               .thenApply(sensitiveStr -> {
                    CommonUtils.printTheadLog("sensitiveStr = " + sensitiveStr);
                    return sensitiveStr.split(",");
                })
                .thenAccept(sensitives -> {
                    String news = CommonUtils.readFile(path + "news.txt");
                    for (String sensitive : sensitives) {
                        if (news.contains(sensitive)) {
                            news = news.replace(sensitive, "**");
                        }
                    }
                    System.out.println("news = " + news);
                });
    }

    @Test
    @SneakyThrows
    public void thenRunTest() {
        CompletableFuture.supplyAsync(() -> CommonUtils.readFile(path + "filter_words.txt"))
                .thenRun(() -> CommonUtils.printTheadLog("读取文件完毕"));

        CommonUtils.printTheadLog("main continue");
        CommonUtils.sleepSecond(2);
        CommonUtils.printTheadLog("main end");
    }

    @Test
    @SneakyThrows
    public void thenSupplyAsyncTest() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletableFuture<String[]> filterWordFuture = CompletableFuture.supplyAsync(()
                -> {
            CommonUtils.printTheadLog("读取filter_words.txt文件");
            return CommonUtils.readFile(path + "filter_words.txt");
        }, executor).thenApplyAsync(content -> {
            CommonUtils.printTheadLog("把文件内容转换成敏感词数组");
            return content.split(",");
        }, executor);

        CommonUtils.printTheadLog("main continue");
        String[] filterWords = filterWordFuture.get();
        CommonUtils.printTheadLog("filterWords = " +
                Arrays.toString(filterWords));
        executor.shutdown();
        CommonUtils.printTheadLog("main end");
    }

}
