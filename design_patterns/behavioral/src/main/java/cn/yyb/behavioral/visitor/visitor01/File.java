package cn.yyb.behavioral.visitor.visitor01;

import lombok.Getter;

/**
 * @author yueyubo <br>
 * @date 2024-06-05 20:36
 */
@Getter
public class File extends Entry {
    private final String name;
    private final int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}