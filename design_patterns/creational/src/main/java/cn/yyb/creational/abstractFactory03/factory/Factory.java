package cn.yyb.creational.abstractFactory03.factory;

/**
 * 抽象工厂
 * @author yueyubo <br>
 * @date 2024-06-02 15:25
 */
public abstract class Factory {
    public static Factory getFactory(Class<?> clazz){
        Factory factory;
        try {
            factory = (Factory) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return factory;
    }

    public abstract Link createLink(String caption, String url);
    public abstract Tray createTray(String caption);
    public abstract Page createPage(String title, String author);
}
