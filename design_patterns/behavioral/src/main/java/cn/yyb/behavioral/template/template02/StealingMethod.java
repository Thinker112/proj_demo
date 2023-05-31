package cn.yyb.behavioral.template.template02;


import lombok.extern.slf4j.Slf4j;

/**
 * @author yyb
 * @create 2022-11-27 10:51
 */
@Slf4j
public abstract class StealingMethod {


    protected abstract String pickTarget();

    protected abstract void confuseTarget(String target);

    protected abstract void stealTheItem(String target);

    public void steal() {
        String target = pickTarget();
        log.debug("The target has been chosen as {}.", target);
        confuseTarget(target);
        stealTheItem(target);
    }
}
