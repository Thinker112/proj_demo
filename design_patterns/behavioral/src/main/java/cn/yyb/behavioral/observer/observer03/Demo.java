package cn.yyb.behavioral.observer.observer03;

/**
 * @author yyb
 * @create 2022-12-26 22:54
 */
public class Demo {
    public static void main(String[] args) {

        Editor editor = new Editor();
        editor.events.subscribe("open", new LogOpenListener("./log/file.txt"));
        editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));

        try {
            editor.openFile("test.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
