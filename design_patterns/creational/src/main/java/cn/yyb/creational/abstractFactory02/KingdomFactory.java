package cn.yyb.creational.abstractFactory02;

import cn.yyb.creational.abstractFactory02.Army;
import cn.yyb.creational.abstractFactory02.Castle;
import cn.yyb.creational.abstractFactory02.King;

public interface KingdomFactory {
    Castle createCastle();
    King createKing();
    Army createArmy();
}
