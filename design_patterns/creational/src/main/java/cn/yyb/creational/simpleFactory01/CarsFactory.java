package cn.yyb.creational.simpleFactory01;

/**
 * 工厂类，用于根据CarType创建Car实例
 */
public class CarsFactory {

    public static Car getCar(CarType carType){
        return carType.getConstructor().get();
    }
}
