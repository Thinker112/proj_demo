package cn.yyb.creational.abstractFactory03.listfactory;

import cn.yyb.creational.abstractFactory03.factory.Link;

/**
 * @author yueyubo <br>
 * @date 2024-06-02 15:37
 */
public class ListLink extends Link {

    public ListLink(String caption, String url) {
        super(caption, url);
    }

    @Override
    public String makeHTML() {
        return "<li><a href=\"" + url + "\">" + caption + "</a></li>\n";
    }
}
