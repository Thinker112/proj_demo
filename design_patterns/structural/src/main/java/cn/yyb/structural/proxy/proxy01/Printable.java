package cn.yyb.structural.proxy.proxy01;

/**
 * @author yueyubo
 * @date 2024-06-15
 */
public interface Printable {
    void setPrinterName(String name);   // 设置名字
    String getPrinterName();            // 获取名字
    void print(String string);          // 显示文字（打印输出）
}
