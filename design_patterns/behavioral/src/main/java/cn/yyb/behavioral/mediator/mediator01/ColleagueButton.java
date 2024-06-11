package cn.yyb.behavioral.mediator.mediator01;

import java.awt.*;

/**
 * @author yueyubo
 * @date 2024-06-11
 */
public class ColleagueButton extends Button implements Colleague{
    private Mediator mediator;

    public ColleagueButton(String caption) {
        super(caption);
    }

    @Override
    public void setMediator(Mediator mediator) { // 保存Mediator
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEnabled(boolean enabled) { // Mediator下达启用/禁用的指示
        setEnabled(enabled);
    }
}
