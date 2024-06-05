package cn.yyb.behavioral.visitor.visitor01;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author yueyubo <br>
 * @date 2024-06-05 20:38
 */
public class Directory extends Entry {
    // 文件夹名字
    @Getter
    private final String name;

    // 目录条目集合
    private final ArrayList<Entry> dir = new ArrayList<>();

    public Directory(String name) { // 构造函数
        this.name = name;
    }

    public int getSize() {
        return dir.size();
    }

    public Entry add(Entry entry) { // 增加目录条目
        dir.add(entry);
        return this;
    }

    public Iterator<Entry> iterator() { // 生成Iterator
        return dir.iterator();
    }

    public void accept(Visitor v) { // 接受访问者的访问
        v.visit(this);
    }
}