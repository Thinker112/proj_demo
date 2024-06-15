package cn.yyb.structural.proxy.proxy01;

/**
 * @author yueyubo
 * @date 2024-06-15
 */
public class Main {
    public static void main(String[] args) {
        Printable p = new PrinterProxy("Alice", "cn.yyb.structural.proxy.proxy01.Printer");
        System.out.println("现在的名字是" + p.getPrinterName() + "。");
        p.setPrinterName("Bob");
        System.out.println("现在的名字是" + p.getPrinterName() + "。");
        p.print("Hello, world.");
    }
}
