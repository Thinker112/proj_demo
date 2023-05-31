package cn.yyb.creational.abstractFactory02.impl;

import cn.yyb.creational.abstractFactory02.Army;

public class ElfArmy implements Army {
    static final String DESCRIPTION = "This is the Elven Army!";
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
