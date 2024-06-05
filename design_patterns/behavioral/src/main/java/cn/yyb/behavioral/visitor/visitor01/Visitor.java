package cn.yyb.behavioral.visitor.visitor01;


/**
 * 表示访问者的抽象类 （对数据结构进行处理） <br>
 * Visitor模式的目的：将处理从数据结构中分离出来
 * @author yueyubo <br>
 * @date 2024-06-05 20:26
 */
public abstract class Visitor {
    public abstract void visit(File file);
    public abstract void visit(Directory directory);
}