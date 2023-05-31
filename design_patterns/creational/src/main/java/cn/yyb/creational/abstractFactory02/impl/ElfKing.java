package cn.yyb.creational.abstractFactory02.impl;

import cn.yyb.creational.abstractFactory02.King;

public class ElfKing implements King {
    static final String DESCRIPTION = "This is the Elven king!";
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
