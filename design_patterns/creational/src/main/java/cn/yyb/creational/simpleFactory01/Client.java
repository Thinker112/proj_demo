package cn.yyb.creational.simpleFactory01;

public class Client {
    public static void main(String[] args) {
        Ford ford = (Ford) CarsFactory.getCar(CarType.FORD);
        Ferrari ferrari = (Ferrari) CarsFactory.getCar(CarType.FERRARI);

        System.out.println(ferrari.getDescription());
        System.out.println(ford.getDescription());
    }
}
