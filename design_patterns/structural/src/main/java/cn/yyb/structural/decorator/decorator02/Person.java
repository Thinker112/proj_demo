package cn.yyb.structural.decorator.decorator02;

/**
 * (ConcreteComponent)
 * Person类可以通过Decorator在不修改代码的情况下进行增强。
 * @author yyb <br>
 * @date 2024-05-21 <br>
 */
public class Person implements Character{
    private String name;

    public Person(String name) {
        this.name = name;
    }
    @Override
    public void show() {
        System.out.println("装扮的" + name);
    }
}
