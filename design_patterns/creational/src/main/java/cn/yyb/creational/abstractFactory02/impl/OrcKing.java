package cn.yyb.creational.abstractFactory02.impl;

import cn.yyb.creational.abstractFactory02.King;

public class OrcKing implements King {
    static final String DESCRIPTION = "This is the Orc King!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
