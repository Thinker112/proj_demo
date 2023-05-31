package cn.yyb.behavioral.observer.observer03;

import java.io.File;

/**
 * 具体发布者， 由其他对象追踪
 * @author yyb
 * @create 2022-12-26 22:36
 */
public class Editor {
    public EventManager events;
    private File file;

    public Editor() {
        this.events = new EventManager("open", "save");
    }

    public void openFile(String filePath) {
        this.file = new File(filePath);
        events.notify("open", file);
    }

    public void saveFile() throws Exception {
        if (this.file != null) {
            events.notify("save", file);
        } else {
            throw new Exception("Please open a file first.");
        }
    }
}
