package cn.yyb.structural.decorator.decorator02;

/**
 * 服饰类
 * @author yyb <br>
 * @date 2024-05-21 <br>
 */
public class FineryDecorator implements Character{

    protected Character component;

    public void decorate(Character component) {
        this.component = component;
    }

    @Override
    public void show() {
        if (component != null){
            component.show();
        }
    }
}
