package cn.yyb.creational.abstractFactory03.factory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * 抽象的表示HTML页面的类 （抽象的产品）
 * @author yueyubo <br>
 * @date 2024-06-02 15:18
 */
public abstract class Page {
    protected String title;
    protected String author;
    protected ArrayList<Item> content = new ArrayList<>();

    public Page(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void add(Item item) {
        content.add(item);
    }

    /**
     * 模板方法
     */
    public void output(){
        String filename = title + ".html";
        try {
            Writer writer = new FileWriter(filename);
            writer.write(this.makeHTML());
            writer.close();

            System.out.println(filename + " 编写完成");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract String makeHTML();
}
