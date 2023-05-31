package cn.yyb.creational.abstractFactory02.impl;

import cn.yyb.creational.abstractFactory02.Army;

public class OrcArmy implements Army {

    static final String DESCRIPTION = "This is the Orc Army!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
