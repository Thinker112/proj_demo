package cn.yyb.behavioral.observer.observer03;


import java.io.File;

/**
 * 收到通知后发送邮件
 * @author yyb
 * @create 2022-12-26 22:44
 */
public class EmailNotificationListener implements EventListener{

    private String email;

    public EmailNotificationListener(String email) {
        this.email = email;
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Email to " + email + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }
}
