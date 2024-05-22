package cn.yyb.structural.decorator.decorator02;

/**
 * @author yueyubo <br>
 * @date 2024-05-21 22:48
 */
public class Client {
    public static void main(String[] args) {
        Person ming = new Person("小明");
        TShirtsDecorator tShirts = new TShirtsDecorator();
        tShirts.decorate(ming);

        SneakerDecorator sneaker = new SneakerDecorator();
        sneaker.decorate(tShirts);

        sneaker.show();

    }
}
