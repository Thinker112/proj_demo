package cn.yyb.structural.decorator.decorator01;

/**
 * @author yyb <br>
 * @date 2024-05-21
 */
public class Decorator extends Component{

    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if (component != null){
            component.operation();
        }
    }
}
