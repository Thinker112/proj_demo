package cn.yyb.creational.abstractFactory03.factory;

import java.util.ArrayList;

/**
 * 表示含有多个Link类和Tray类的容器
 * @author yueyubo <br>
 * @date 2024-06-02 15:16
 */
public abstract class Tray extends Item{
    protected ArrayList<Item> tray = new ArrayList<>();

    public Tray(String caption) {
        super(caption);
    }

    public void addItem(Item item) {
        tray.add(item);
    }
}
