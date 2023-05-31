package cn.yyb.creational.abstractFactory02.impl;

import cn.yyb.creational.abstractFactory02.Castle;

public class OrcCastle implements Castle {
    static final String DESCRIPTION = "This is the Orc Castle!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
