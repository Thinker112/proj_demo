package cn.yyb.creational.abstractFactory03.listfactory;

import cn.yyb.creational.abstractFactory03.factory.Factory;
import cn.yyb.creational.abstractFactory03.factory.Link;
import cn.yyb.creational.abstractFactory03.factory.Page;
import cn.yyb.creational.abstractFactory03.factory.Tray;

/**
 * @author yueyubo <br>
 * @date 2024-06-02 15:39
 */
public class ListFactory extends Factory {

    @Override
    public Link createLink(String caption, String url) {
        return new ListLink(caption, url);
    }

    @Override
    public Tray createTray(String caption) {
        return new ListTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}
