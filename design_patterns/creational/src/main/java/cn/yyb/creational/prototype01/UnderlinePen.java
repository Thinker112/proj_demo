package cn.yyb.creational.prototype01;

import cn.yyb.creational.prototype01.framework.Product;

/**
 * ConcretePrototype
 * @author yueyubo <br>
 * @date 2024-05-30 21:36
 */
public class UnderlinePen extends Product {
    private char ulchar;

    public UnderlinePen(char ulchar) {
        this.ulchar = ulchar;
    }

    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        System.out.println("\"" + s + "\"");
        for (int i = 0; i < length; i++) {
            System.out.print(ulchar);
        }
        System.out.println();
    }
}
