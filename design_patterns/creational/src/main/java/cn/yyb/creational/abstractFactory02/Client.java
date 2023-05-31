package cn.yyb.creational.abstractFactory02;

import cn.yyb.creational.abstractFactory02.impl.ElfKingdomFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 要创建一个王国，我们需要具有共同主题的对象。<br>
 * 精灵王国需要精灵王，精灵城堡和精灵军队，而兽人王国需要兽王，精灵城堡和兽人军队。王国中的对象之间存在依赖性。
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        var factory = new ElfKingdomFactory();
        var castle = factory.createCastle();
        var king = factory.createKing();
        var army = factory.createArmy();

        System.out.println(castle.getDescription());
        System.out.println(king.getDescription());
        System.out.println(army.getDescription());

        KingdomFactory kingdomFactory = FactoryMaker.makeFactory(FactoryMaker.KingdomType.ELF);
        log.info(kingdomFactory.createArmy().getDescription());
        log.info(kingdomFactory.createCastle().getDescription());
        log.info(kingdomFactory.createKing().getDescription());

    }
}
