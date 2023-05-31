package cn.yyb.behavioral.template.template02;


/**
 * @author yyb
 * @create 2022-11-27 10:54
 */

public class SubtleMethod extends StealingMethod {

    @Override
    protected String pickTarget() {
        return "shop keeper";
    }

    @Override
    protected void confuseTarget(String target) {
        System.out.println("Approach the " + target + " with tears running and hug him!");
    }

    @Override
    protected void stealTheItem(String target) {
        System.out.println("While in close contact grab the " + " 's wallet.");
    }
}
