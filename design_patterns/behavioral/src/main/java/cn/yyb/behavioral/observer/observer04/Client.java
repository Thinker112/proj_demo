package cn.yyb.behavioral.observer.observer04;


/**
 * @author yyb
 * @create 2022-12-26 23:25
 */
public class Client {
    public static void main(String[] args) {

        NewsOffice newsOffice = new NewsOffice();
        newsOffice.subscribe(new User("Tom"));
        newsOffice.subscribe(new User("Jerry"));

        newsOffice.notifyNews("2022/12/26 | today news is merry chrisrmas");
    }
}
