package cn.yyb.behavioral.observer.observer01;

/**
 * @author yueyubo
 * @create 2022-11-01 21:22
 */
public class StockObserver {
    String name;
    Subject sub;

    public StockObserver(String name, Subject sub) {
        this.name = name;
        this.sub = sub;
    }

    public StockObserver() {
    }


    public void closeStockMarket(String action) {
        System.out.println(name + " 关闭股票行情 继续工作 " + action);
    }
}
