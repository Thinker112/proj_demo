package cn.yyb.behavioral.observer.observer01;

/**
 * @author yueyubo
 * @create 2022-11-01 22:53
 */
public class Client {
    public static void main(String[] args) {
        Secretary secretary = new Secretary();
        Boss boss = new Boss();
        StockObserver tom = new StockObserver("Tom", secretary);
        StockObserver jerry = new StockObserver("Jerry", secretary);
        NBAObserver kim = new NBAObserver("Kim", secretary);

//        secretary.add(tom);
//        secretary.add(jerry);
//        secretary.add(kim);

    }
}
