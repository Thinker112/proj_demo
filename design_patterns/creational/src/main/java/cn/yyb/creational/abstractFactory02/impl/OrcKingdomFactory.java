package cn.yyb.creational.abstractFactory02.impl;


import cn.yyb.creational.abstractFactory02.Army;
import cn.yyb.creational.abstractFactory02.Castle;
import cn.yyb.creational.abstractFactory02.King;
import cn.yyb.creational.abstractFactory02.KingdomFactory;

public class OrcKingdomFactory implements KingdomFactory {
    public Castle createCastle() {
        return new OrcCastle();
    }
    public King createKing() {
        return new OrcKing();
    }
    public Army createArmy() {
        return new OrcArmy();
    }
}