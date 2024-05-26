package cn.yyb.structural.adapter.adapter02;

/**
 * 对象适配器模式(使用委托的适配器）
 * @author yueyubo <br>
 * @date 2024-05-26 14:29
 */
public class PrintBanner2Adapter implements Print {

    private final Banner banner;

    public PrintBanner2Adapter(String string) {
        this.banner = new Banner(string);
    }

    public void printWeak() {
        banner.showWithParen();
    }

    public void printStrong() {
        banner.showWithAster();
    }
}
