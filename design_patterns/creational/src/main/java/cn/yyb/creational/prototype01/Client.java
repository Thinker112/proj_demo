package cn.yyb.creational.prototype01;


import cn.yyb.creational.prototype01.framework.Manger;
import cn.yyb.creational.prototype01.framework.Product;

/**
 * @author yueyubo <br>
 * @date 2024-05-30 21:38
 */
public class Client {
    public static void main(String[] args) {
        Manger manger = new Manger();
        UnderlinePen underlinePen = new UnderlinePen('~');
        MessageBox messageBox = new MessageBox('*');

        manger.register("strong message", underlinePen);
        manger.register("warning box", messageBox);

        //生成
        Product p1 = manger.create("strong message");
        p1.use("Hello, world.");
        Product p2 = manger.create("warning box");
        p2.use("Hello, world.");
    }
}
