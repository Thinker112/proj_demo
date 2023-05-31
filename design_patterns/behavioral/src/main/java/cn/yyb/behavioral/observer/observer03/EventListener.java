package cn.yyb.behavioral.observer.observer03;

import java.io.File;

/**
 * 通用观察者接口
 * @author yyb
 * @create 2022-12-26 22:38
 */
public interface  EventListener {
    void update(String eventType, File file);
}
