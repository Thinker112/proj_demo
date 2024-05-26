package cn.yyb.structural.adapter.adapter02;

/**
 * 类适配器模式(使用继承的适配器）
 * @author yueyubo <br>
 * @date 2024-05-26 14:21
 */
public class PrintBannerAdapter extends Banner implements Print{

    public PrintBannerAdapter(String string) {
        super(string);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printStrong() {
        showWithAster();
    }
}
