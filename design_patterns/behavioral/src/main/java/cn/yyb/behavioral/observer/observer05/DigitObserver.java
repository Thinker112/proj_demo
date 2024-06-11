package cn.yyb.behavioral.observer.observer05;

/**
 * @author yueyubo
 * @date 2024-06-11
 */
public class DigitObserver implements Observer {
    public void update(NumberGenerator generator) {
        System.out.println("DigitObserver:" + generator.getNumber());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }
}