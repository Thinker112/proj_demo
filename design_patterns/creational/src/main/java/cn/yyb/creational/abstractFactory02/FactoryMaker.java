package cn.yyb.creational.abstractFactory02;

import cn.yyb.creational.abstractFactory02.impl.ElfKingdomFactory;
import cn.yyb.creational.abstractFactory02.impl.OrcKingdomFactory;

public class FactoryMaker {

    public enum KingdomType {
        ELF,
        ORC
    }

    public static KingdomFactory makeFactory(KingdomType type) {
        switch (type) {
            case ELF:
                return new ElfKingdomFactory();
            case ORC:
                return new OrcKingdomFactory();
            default:
                throw new IllegalArgumentException("KingdomType not supported.");
        }
    }
}
