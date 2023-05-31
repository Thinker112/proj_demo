package cn.yyb.behavioral.template.template02;

/**
 * @author yyb
 * @create 2022-11-27 10:58
 */
public class HalflingThief {

    private StealingMethod method;

    public HalflingThief(StealingMethod method) {
        this.method = method;
    }

    public void steal() {
        method.steal();
    }

    public void changeMethod(StealingMethod method) {
        this.method = method;
    }
}
