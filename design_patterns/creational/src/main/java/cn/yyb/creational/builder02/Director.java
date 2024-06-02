package cn.yyb.creational.builder02;

/**
 * 使用Builder类中声明的方法来编写文档
 * @author yueyubo <br>
 * @date 2024-06-02 13:47
 */
public class Director {
    private final Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.makeTitle("Greeting");
        builder.makeString("From morning To afternoon");

        builder.makeItems(new String[]{"good morning", "good afternoon"});
        builder.makeString("晚上");

        builder.makeItems(new String[]{"good evening", "goodbye"});

        builder.close();
    }
}
