package cn.yyb.behavioral.memento.memento01.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式
 * 表示Gamer状态的类
 * @author yueyubo
 * @date 2024-06-12
 */
public class Memento implements Serializable {
    int money;   // 所持金钱
    private int number;
    ArrayList<String> fruits;   // 当前获得的水果

    public int getMoney() {  // 获取当前所持金钱（narrow interface）
        return money;
    }

    protected int getNumber() {
        return number;
    }

    Memento(int money) {  // 构造函数(wide interface)
        this.money = money;
        this.fruits = new ArrayList<>();
    }

    void addFruit(String fruit) {  // 添加水果(wide interface)
        fruits.add(fruit);
    }

    List<String> getFruits() {  // 获取当前所持所有水果（wide interface）
        return (List<String>) fruits.clone();
    }
}