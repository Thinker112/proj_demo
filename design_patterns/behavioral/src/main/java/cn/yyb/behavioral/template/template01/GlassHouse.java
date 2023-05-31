package cn.yyb.behavioral.template.template01;

/**
 * @author yyb
 * @create 2022-11-27 11:18
 */
public class GlassHouse extends HouseTemplate{
    @Override
    public void buildWalls() {
        System.out.println("Building Glass Walls");
    }

    @Override
    public void buildPillars() {
        System.out.println("Building Pillars with glass coating");
    }
}
