package cn.yyb.behavioral.observer.observer04;

/**
 * @author yyb
 * @create 2022-12-26 23:09
 */
public class User implements NewsListener{

    private String name;

    public User(String name) {
        this.name = name;
    }


    @Override
    public void update(String news) {
        System.out.println("用户：" + name + "收到了新的报纸-> " + news);
    }
}
