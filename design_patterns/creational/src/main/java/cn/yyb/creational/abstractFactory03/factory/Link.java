package cn.yyb.creational.abstractFactory03.factory;

/**
 * 抽象的零件
 * @author yueyubo <br>
 * @date 2024-06-02 15:14
 */
public abstract class Link extends Item{
    protected String url;

    public Link(String caption, String url) {
        super(caption);
        this.url = url;
    }
}
