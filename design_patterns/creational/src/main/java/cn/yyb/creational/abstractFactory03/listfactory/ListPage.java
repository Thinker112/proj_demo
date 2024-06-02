package cn.yyb.creational.abstractFactory03.listfactory;

import cn.yyb.creational.abstractFactory03.factory.Item;
import cn.yyb.creational.abstractFactory03.factory.Page;

/**
 * @author yueyubo <br>
 * @date 2024-06-02 15:44
 */
public class ListPage extends Page {
    public ListPage(String title, String author) {
        super(title, author);
    }

    @Override
    public String makeHTML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<html><head><title>").append(title).append("</title></head>\n");
        buffer.append("<body>\n");
        buffer.append("<h1>").append(title).append("</h1>\n");
        buffer.append("<ul>\n");
        for (Item item : content) {
            buffer.append(item.makeHTML());
        }
        buffer.append("</ul>\n");
        buffer.append("<hr><address>").append(author).append("</address>");
        buffer.append("</body></html>\n");

        return buffer.toString();
    }
}
