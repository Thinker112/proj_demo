package cn.yyb.structural.decorator.decorator03;

/**
 * @author yueyubo <br>
 * @date 2024-06-04 22:03
 */
public abstract class Border extends Display {
    protected Display display;          // 表示被装饰物

    protected Border(Display display) { // 在生成实例时通过参数指定被装饰物
        this.display = display;
    }
}