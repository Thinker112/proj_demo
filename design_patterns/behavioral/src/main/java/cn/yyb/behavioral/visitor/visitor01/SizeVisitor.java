package cn.yyb.behavioral.visitor.visitor01;

import java.util.Iterator;

/**
 * @author yueyubo <br>
 * @date 2024-06-05 21:54
 */
public class SizeVisitor extends Visitor {
    private int size = 0;
    public int getSize() {
        return size;
    }
    public void visit(File file) {
        size += file.getSize();
    }
    public void visit(Directory directory) {
        Iterator<Entry> it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = it.next();
            entry.accept(this);
        }
    }
}