package cn.yyb.structural.decorator.decorator01;

import lombok.Setter;

/**
 * @author yyb <br>
 * @date 2024-05-21
 */
@Setter
public class Decorator extends Component{

    protected Component component;

    @Override
    public void operation() {
        if (component != null){
            component.operation();
        }
    }
}
