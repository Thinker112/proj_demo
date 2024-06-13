package cn.yyb.structural.flyweight.flyweight01;

/**
 * @author yueyubo
 * @date 2024-06-13
 */
public class BigString {
    // “大型字符”的数组
    private BigChar[] bigchars;

    // 构造函数
    public BigString(String string, boolean shared) {
        if (shared) {
            initShared(string);
        } else {
            initUnshared(string);
        }
    }
    // 共享方式初始化
    private void initShared(String string) {
        bigchars = new BigChar[string.length()];
        BigCharFactory factory = BigCharFactory.getInstance();
        for (int i = 0; i < bigchars.length; i++) {
            bigchars[i] = factory.getBigChar(string.charAt(i));
        }
    }
    // 非共享方式初始化
    private void initUnshared(String string) {
        bigchars = new BigChar[string.length()];
        for (int i = 0; i < bigchars.length; i++) {
            bigchars[i] = new BigChar(string.charAt(i));
        }
    }

    // 显示
    public void print() {
        for (BigChar bigchar : bigchars) {
            bigchar.print();
        }
    }
}
