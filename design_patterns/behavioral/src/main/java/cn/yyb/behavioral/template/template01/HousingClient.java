package cn.yyb.behavioral.template.template01;

/**
 * @author yyb
 * @create 2022-11-27 11:18
 */
public class HousingClient {
    public static void main(String[] args) {
        HouseTemplate houseType = new WoodenHouse();

        //using template method
        houseType.buildHouse();
        System.out.println("************");

        houseType = new GlassHouse();

        houseType.buildHouse();
    }
}
