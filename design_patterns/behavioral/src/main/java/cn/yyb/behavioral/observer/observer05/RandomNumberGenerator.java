package cn.yyb.behavioral.observer.observer05;

import java.util.Random;

/**
 * @author yueyubo
 * @date 2024-06-11
 */
public class RandomNumberGenerator extends NumberGenerator {
    private final Random random = new Random();   // 随机数生成器
    private int number;                     // 当前数值

    public int getNumber() {                // 获取当前数值
        return number;
    }

    public void execute() {
        for (int i = 0; i < 20; i++) {
            number = random.nextInt(50);
            notifyObservers();
        }
    }
}