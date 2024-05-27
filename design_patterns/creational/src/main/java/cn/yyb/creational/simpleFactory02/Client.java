package cn.yyb.creational.simpleFactory02;

import cn.yyb.creational.simpleFactory02.framework.Product;
import cn.yyb.creational.simpleFactory02.idcard.IDCardFactory;

/**
 * @author yueyubo <br>
 * @date 2024-05-27 21:23
 */
public class Client {
    public static void main(String[] args) {
        IDCardFactory factory = new IDCardFactory();
        Product card1 = factory.create("tom", "Y001");
        Product card2 = factory.create("jerry", "Y002");
        Product card3 = factory.create("henry", "Y003");

        card1.use();
        card2.use();
        card3.use();

    }
}
