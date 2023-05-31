package cn.yyb.behavioral.observer.observer02;

/**
 * @author yueyubo
 * @create 2022-10-28 0:10
 */
public interface Subject {

    //注册观察者
    void registerObserver(Observer observer);
    //移除观察者
    void removeObserver(Observer observer);
    //通知观察者
    void notifyObserver();
}
