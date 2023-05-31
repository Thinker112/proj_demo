package cn.yyb.behavioral.observer.observer04;

import java.util.ArrayList;
import java.util.List;

/**
 * 报社（发布者）
 * @author yyb
 * @create 2022-12-26 23:06
 */
public class NewsOffice {

    //订阅报纸的用户
    private List<User> userList;

    public NewsOffice() {
        this.userList = new ArrayList<>();
    }

    //添加订阅报纸的用户
    public void subscribe(User user){
        userList.add(user);
    }

    //用户取消订阅报纸
    public void unSubscribe(User user){
        if (userList.contains(user)){
            userList.remove(user);
        }else {
            System.out.println("用户不存在");
        }
    }

    //有新报纸了，通知订阅报纸的用户
    public void notifyNews(String news){
        for (User user : userList) {
            user.update(news);
        }
    }
}
