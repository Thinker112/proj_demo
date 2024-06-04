package cn.yyb.structural.composite.composite04;

import lombok.Getter;

import java.util.ArrayList;

/**
 * @author yueyubo <br>
 * @date 2024-06-04 20:44
 */
public class Directory extends Entry{
    // 获取名字
    @Getter
    private final String name; // 文件夹的名字

    // 文件夹中目录条目的集合
    private final ArrayList<Entry> directory = new ArrayList<>();

    public Directory(String name) {         // 构造函数
        this.name = name;
    }

    public int getSize() {  // 获取大小
        return directory.size();
    }

    public Entry add(Entry entry) {  // 增加目录条目
        directory.add(entry);
        entry.parent = this;
        return this;
    }

    protected void printList(String prefix) { // 显示目录条目一览
        System.out.println(prefix + "/" + this);
        for (Entry entry : directory) {
            entry.printList(prefix + "/" + name);
        }
    }

}
