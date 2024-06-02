package cn.yyb.creational.builder02;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yueyubo <br>
 * @date 2024-06-02 14:02
 */
public class HTMLBuilder extends BaseBuilder{
    private String filename;

    private PrintWriter writer;//用于编写文件

    @Override
    public void buildTitle(String title) {
        filename = title + ".html";
        try {
            writer = new PrintWriter(new FileWriter(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        writer.println("<html><head><title>" + title + "</title></head><body>");
        //输出标题
        writer.println("<h1>" + title + "</h1>");
    }

    @Override
    public void buildString(String str) {
        writer.println("<p>" + str + "</p>");
    }

    @Override
    public void buildItems(String[] items) {
        writer.println("<ul>");
        for (String item : items) {
            writer.println("<li>" + item + "</li>");
        }
        writer.println("</ul>");
    }

    @Override
    public void buildClose() {
        writer.println("</body></html>");
        writer.close();
    }

    public String getResult(){
        return filename;
    }
}
