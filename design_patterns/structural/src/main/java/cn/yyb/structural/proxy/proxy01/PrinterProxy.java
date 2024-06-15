package cn.yyb.structural.proxy.proxy01;

import java.lang.reflect.InvocationTargetException;

/**
 * @author yueyubo
 * @date 2024-06-15
 */
public class PrinterProxy implements Printable {
    private String name;            // 名字
    private Printable real;           // “本人”
    private String clazzName; // “本人”的类名

    public PrinterProxy() {
    }

    public PrinterProxy(String name, String clazzName) {      // 构造函数
        this.name = name;
        this.clazzName = clazzName;
    }

    public synchronized void setPrinterName(String name) {  // 设置名字
        if (real != null) {
            real.setPrinterName(name);  // 同时设置“本人”的名字
        }
        this.name = name;
    }

    public String getPrinterName() {    // 获取名字
        return name;
    }

    public void print(String string) {  // 显示
        realize();
        real.print(string);
    }

    private synchronized void realize() {   // 生成“本人”
        if (real == null) {
            try {
                real = (Printable) Class.forName(clazzName).getDeclaredConstructor().newInstance();
                real.setPrinterName(name);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
