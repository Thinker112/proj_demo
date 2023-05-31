package cn.yyb.creational.simpleFactory01;

/**
 * 福特
 */
public class Ford implements Car{

    static final String DESCRIPTION = "This is Ford.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
