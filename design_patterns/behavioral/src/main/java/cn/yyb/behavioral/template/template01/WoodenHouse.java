package cn.yyb.behavioral.template.template01;

/**
 * @author yyb
 * @create 2022-11-27 11:17
 */
public class WoodenHouse extends HouseTemplate{
    @Override
    public void buildWalls() {
        System.out.println("Building Wooden Walls");
    }

    @Override
    public void buildPillars() {
        System.out.println("Building Pillars with Wood coating");
    }
}
