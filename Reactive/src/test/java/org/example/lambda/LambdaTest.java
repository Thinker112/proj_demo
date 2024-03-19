package org.example.lambda;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class LambdaTest {


    /**
     * 编译后会生成两个.class文件。 LambdaTest.class 与 LambdaTest$1.class(带$表示匿名内部类) <br>
     * 关于闭包：<a href="https://www.zhihu.com/question/21395848#/">java为什么匿名内部类的参数引用时final？</a>
     * @throws InterruptedException
     */
    @Test
    public void testClosure() throws InterruptedException {
        // 在匿名内部类的外面定义一个String变量
        final String str = "hello";
        // 构造一个匿名内部类对象
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(str);
                System.out.println("this 当前对象: " + this);
            }
        };

        new Thread(r).start();

        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * 改为Lambda后，编译后生成一个.class文件。 LambdaTest.class
     * 且this 当前对象指向的是LambdaTest
     * @throws InterruptedException
     */
    @Test
    public void testClosure2() throws InterruptedException {
        // 在匿名内部类的外面定义一个String变量
        final String str = "hello";
        // 构造一个匿名内部类对象
        Runnable r = () -> {
            System.out.println(str);
            System.out.println("this 当前对象: " + this);
        };

        new Thread(r).start();

        TimeUnit.SECONDS.sleep(1);
    }
}
