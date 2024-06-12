package cn.yyb.behavioral.memento.memento01;

import cn.yyb.behavioral.memento.memento01.game.Gamer;
import cn.yyb.behavioral.memento.memento01.game.Memento;

import java.io.*;

/**
 * @author yueyubo
 * @date 2024-06-12
 */
public class Main {
//    public static void main(String[] args) {
//        Gamer gamer = new Gamer(100);               // 最初的所持金钱数为100
//        Memento memento = gamer.createMemento();    // 保存最初的状态
//        for (int i = 0; i < 100; i++) {
//            System.out.println("==== " + i);        // 显示掷骰子的次数
//            System.out.println("当前状态:" + gamer);    // 显示主人公现在的状态
//
//            gamer.bet();    // 进行游戏
//
//            System.out.println("所持金钱为" + gamer.getMoney() + "元。");
//
//            // 决定如何处理Memento
//            if (gamer.getMoney() > memento.getMoney()) {
//                System.out.println("    （所持金钱增加了许多，因此保存游戏当前的状态）");
//                memento = gamer.createMemento();
//            } else if (gamer.getMoney() < memento.getMoney() / 2) {
//                System.out.println("    （所持金钱减少了许多，因此将游戏恢复至以前的状态）");
//                gamer.restoreMemento(memento);
//            }
//
//            // 等待一段时间
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//            }
//            System.out.println();
//        }
//    }

    public static final String SAVEFILENAME = "game.dat";
    public static void main(String[] args) {
        Gamer gamer = new Gamer(100);               // 最初的所持金钱数为100
        Memento memento = loadMemento();            // 从文件中读取起始状态
        if (memento != null) {
            System.out.println("读取上次保存存档开始游戏。");
            gamer.restoreMemento(memento);
        } else {
            System.out.println("新游戏。");
            memento = gamer.createMemento();
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("==== " + i);        // 显示次数
            System.out.println("当前状态:" + gamer);    // 显示当前主人公的状态

            gamer.bet();    // 进行游戏

            System.out.println("所持金钱为" + gamer.getMoney() + "元。");

            // 决定如何处理Memento
            if (gamer.getMoney() > memento.getMoney()) {
                System.out.println("    （所持金钱增加了许多，因此保存游戏当前的状态）");
                memento = gamer.createMemento();
                saveMemento(memento);   //  保存至文件
            } else if (gamer.getMoney() < memento.getMoney() / 2) {
                System.out.println("    （所持金钱减少了许多，因此将游戏恢复至以前的状态）");
                gamer.restoreMemento(memento);
            }

            // 等待一段时间
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("");
        }
    }
    public static void saveMemento(Memento memento) {
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(SAVEFILENAME));
            out.writeObject(memento);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Memento loadMemento() {
        Memento memento = null;
        try {
            ObjectInput in = new ObjectInputStream(new FileInputStream(SAVEFILENAME));
            memento = (Memento)in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return memento;
    }
}
