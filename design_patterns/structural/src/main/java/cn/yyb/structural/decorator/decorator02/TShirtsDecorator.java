package cn.yyb.structural.decorator.decorator02;

/**
 * 具体服饰类
 * @author yueyubo <br>
 * @date 2024-05-21 22:44
 */
public class TShirtsDecorator extends FineryDecorator{

    @Override
    public void show() {
        System.out.print("大T恤");
        super.show();
    }
}
