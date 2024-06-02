package cn.yyb.creational.builder02;

/**
 * 声明一个编写文档的方法的抽象类
 * @author yueyubo <br>
 * @date 2024-06-02 13:39
 */
public interface Builder {
    void makeTitle(String title);
    void makeString(String str);
    void makeItems(String[] items);
    void close();
}
