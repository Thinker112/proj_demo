package cn.yyb.structural.decorator.decorator01;

/**
 * @author yyb <br>
 * @date 2024-05-21
 */
public class ConcreteDecoratorA extends Decorator{
    //私有字段，区别于ConcreteDecoratorB
    private String addedState;

    public void operation() {
        super.operation();
        addedState = "New State";
        System.out.println("具体装饰对象A的操作" + addedState);
    }
}
