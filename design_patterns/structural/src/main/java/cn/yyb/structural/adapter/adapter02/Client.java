package cn.yyb.structural.adapter.adapter02;

/**
 * @author yueyubo <br>
 * @date 2024-05-26 14:23
 */
public class Client {
    public static void main(String[] args) {
        Print printBanner = new PrintBannerAdapter("Hello");
        printBanner.printStrong();
        printBanner.printWeak();

    }
}
