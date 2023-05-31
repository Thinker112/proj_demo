package cn.yyb.behavioral.observer.observer02;

/**
 * @author yueyubo
 * @create 2022-10-28 0:30
 */
public class Client {
    public static void main(String[] args) {
        WeathData weathData = new WeathData();
        CurrentConditions currentConditions = new CurrentConditions();
        weathData.registerObserver(currentConditions);
        weathData.dataChange(10.0, 43.3, 21.1);

    }
}
