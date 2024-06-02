package cn.yyb.creational.builder02;

/**
 * 编写纯文本文档
 * @author yueyubo <br>
 * @date 2024-06-02 13:54
 */
public class TextBuilder extends BaseBuilder {
    private final StringBuffer buffer = new StringBuffer();

    @Override
    public void buildTitle(String title) {
        buffer.append("===========================\n");
        buffer.append("[").append(title).append("]\n");
        buffer.append("\n");
    }

    @Override
    public void buildString(String str) {
        buffer.append("■").append(str).append("\n");
    }

    @Override
    public void buildItems(String[] items) {
        for (String item : items) {
            buffer.append(" .").append(item).append("\n");
        }
        buffer.append("\n");
    }

    @Override
    public void buildClose() {
        buffer.append("===========================\n");
    }

    public StringBuffer getResult() {
        return buffer;
    }
}
