package cn.yyb.creational.abstractFactory03;

import cn.yyb.creational.abstractFactory03.factory.Factory;
import cn.yyb.creational.abstractFactory03.factory.Link;
import cn.yyb.creational.abstractFactory03.factory.Page;
import cn.yyb.creational.abstractFactory03.factory.Tray;
import cn.yyb.creational.abstractFactory03.listfactory.*;
/**
 * @author yueyubo <br>
 * @date 2024-06-02 15:37
 */
public class Main {
    public static void main(String[] args) {

        Factory factory = Factory.getFactory(ListFactory.class);

        Link people = factory.createLink("人民日报","http://www.people.com.cn/");
        Link gmw = factory.createLink("光明日报","http://www.gmw.cn/");

        Link us_yahoo = factory.createLink("Yahoo!", "http://www.yahoo.com/");
        Link jp_yahoo = factory.createLink("Yahoo!Japan", "http://www.yahoo.co.jp/");
        Link excite = factory.createLink("Excite", "http://www.excite.com/");
        Link google = factory.createLink("Google", "http://www.google.com/");

        Tray tray_news = factory.createTray("日报");
        tray_news.addItem(people);
        tray_news.addItem(gmw);

        Tray trayyahoo = factory.createTray("Yahoo!");
        trayyahoo.addItem(us_yahoo);
        trayyahoo.addItem(jp_yahoo);

        Tray traysearch = factory.createTray("检索引擎");
        traysearch.addItem(trayyahoo);
        traysearch.addItem(excite);
        traysearch.addItem(google);

        Page page = factory.createPage("LinkPage", "杨文轩");
        page.add(tray_news);
        page.add(traysearch);
        page.output();
    }
}
