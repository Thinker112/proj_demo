package cn.yyb.creational.abstractFactory02.impl;

import cn.yyb.creational.abstractFactory02.Army;
import cn.yyb.creational.abstractFactory02.Castle;
import cn.yyb.creational.abstractFactory02.King;
import cn.yyb.creational.abstractFactory02.KingdomFactory;

public class ElfKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new ElfCastle();
    }
    public King createKing() {
        return new ElfKing();
    }
    public Army createArmy() {
        return new ElfArmy();
    }
}
