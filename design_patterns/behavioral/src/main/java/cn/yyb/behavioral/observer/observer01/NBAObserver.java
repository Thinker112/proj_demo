package cn.yyb.behavioral.observer.observer01;

/**
 * @author yueyubo
 * @create 2022-11-01 23:14
 */
public class NBAObserver{
    String name;
    Subject sub;

    public NBAObserver() {
    }

    public NBAObserver(String name, Secretary sub ) {
        this.name = name;
        this.sub = sub;
    }


    void closeNBALive(String action) {
        System.out.println(name + " 关闭NBA直播 继续工作 " + action);
    }
}
