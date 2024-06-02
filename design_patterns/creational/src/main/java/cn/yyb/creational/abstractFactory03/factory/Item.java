package cn.yyb.creational.abstractFactory03.factory;

/**
 * 抽象的零件-Item
 * @author yueyubo <br>
 * @date 2024-06-02 15:12
 */
public abstract class Item {
    //标题
    protected String caption;

    public Item(String caption) {
        this.caption = caption;
    }

    public abstract String makeHTML();
}
