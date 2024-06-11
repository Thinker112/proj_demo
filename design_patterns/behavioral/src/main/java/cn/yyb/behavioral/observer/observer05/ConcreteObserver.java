package cn.yyb.behavioral.observer.observer05;

/**
 * @author yueyubo
 * @date 2024-06-11
 */
public class ConcreteObserver implements Observer {
    @Override
    public void update(NumberGenerator generator) {
        System.out.println("接收到generator:" + generator.getNumber());
    }
}
