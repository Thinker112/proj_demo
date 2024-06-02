package cn.yyb.creational.abstractFactory03.listfactory;

import cn.yyb.creational.abstractFactory03.factory.Item;
import cn.yyb.creational.abstractFactory03.factory.Tray;

import java.util.Iterator;

/**
 * @author yueyubo <br>
 * @date 2024-06-02 15:39
 */
public class ListTray extends Tray {

    public ListTray(String caption) {
        super(caption);
    }

    @Override
    public String makeHTML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<li>\n").append(caption).append("\n<ul>\n");
        for (Item item : tray) {
            buffer.append(item.makeHTML());
        }
        buffer.append("</ul>\n");
        buffer.append("</li>\n");

        return buffer.toString();
    }
}
