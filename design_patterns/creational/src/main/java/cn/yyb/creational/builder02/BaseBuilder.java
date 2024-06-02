package cn.yyb.creational.builder02;

/**
 * 确保在调用 makeString、makeItems 和 close 方法之前只能调用一次 makeTitle 方法
 * @author yueyubo <br>
 * @date 2024-06-02 14:34
 */
public abstract class BaseBuilder implements Builder{
    private boolean titleMade = false;

    @Override
    public void makeTitle(String title) {
        if (titleMade) {
            throw new IllegalStateException("makeTitle can only be called once.");
        }
        titleMade = true;
        buildTitle(title);
    }

    @Override
    public void makeString(String str) {
        ensureTitleMade();
        buildString(str);
    }

    @Override
    public void makeItems(String[] items) {
        ensureTitleMade();
        buildItems(items);
    }

    @Override
    public void close() {
        ensureTitleMade();
        buildClose();
    }

    protected abstract void buildTitle(String title);
    protected abstract void buildString(String str);
    protected abstract void buildItems(String[] items);
    protected abstract void buildClose();

    private void ensureTitleMade() {
        if (!titleMade) {
            throw new IllegalStateException("makeTitle must be called before other methods.");
        }
    }
}
