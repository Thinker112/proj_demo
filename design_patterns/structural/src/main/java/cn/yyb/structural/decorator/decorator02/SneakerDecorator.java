package cn.yyb.structural.decorator.decorator02;

/**
 * @author yueyubo <br>
 * @date 2024-05-21 22:47
 */
public class SneakerDecorator extends FineryDecorator{

    @Override
    public void show() {
        System.out.print("运动鞋");
        super.show();
    }
}
