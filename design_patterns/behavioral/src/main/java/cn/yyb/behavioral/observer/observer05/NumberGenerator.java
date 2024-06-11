package cn.yyb.behavioral.observer.observer05;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 用于生成数值的抽象类(Subject-观察对象)
 *
 * @author yueyubo
 * @date 2024-06-11
 */
public abstract class NumberGenerator {
    private final ArrayList<Observer> observers = new ArrayList<>();        // 保存Observer们

    public void addObserver(Observer observer) {    // 注册Observer
        observers.add(observer);
    }

    public void deleteObserver(Observer observer) { // 删除Observer
        observers.remove(observer);
    }

    public void notifyObservers() {               // 向Observer发送通知
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public abstract int getNumber();                // 获取数值

    public abstract void execute();                 // 生成数值
}
