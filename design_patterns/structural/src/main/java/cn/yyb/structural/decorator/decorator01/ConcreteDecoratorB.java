package cn.yyb.structural.decorator.decorator01;

/**
 * @author yyb <br>
 * @date 2024-05-21
 */
public class ConcreteDecoratorB extends Decorator{
    @Override
    public void operation() {
        super.operation();
        this.addBehaviorB();
    }

    private void addBehaviorB() {
        System.out.println("装饰对象B的独有操作");
    }
}
