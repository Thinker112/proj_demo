package cn.yyb.behavioral.visitor.visitor01;

/**
 * 接受访问者访问的接口
 * @author yueyubo <br>
 * @date 2024-06-05 20:32
 */
public interface Element {
    void accept(Visitor v);
}