package cn.yyb.structural.composite.composite04;

import java.awt.*;

/**
 * @author yueyubo <br>
 * @date 2024-06-04 20:40
 */
public class File extends Entry{
    private final String name;
    //文件大小
    private final int size;

    public File(String name, int size){
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
    }
}
