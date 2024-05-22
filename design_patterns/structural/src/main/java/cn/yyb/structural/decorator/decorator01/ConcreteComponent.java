package cn.yyb.structural.decorator.decorator01;

/**
 * @author yyb <br>
 * @date 2024-05-21
 */
public class ConcreteComponent extends Component{
    @Override
    public void operation() {
        System.out.println("具体对象的操作");
    }
}
