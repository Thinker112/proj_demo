package cn.yyb.creational.simpleFactory01;

/**
 * 法拉利
 */
public class Ferrari implements Car{

    static final String DESCRIPTION = "This is Ferrari.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
