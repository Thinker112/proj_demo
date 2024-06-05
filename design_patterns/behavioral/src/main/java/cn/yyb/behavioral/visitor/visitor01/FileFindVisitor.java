package cn.yyb.behavioral.visitor.visitor01;

import java.nio.file.FileVisitor;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author yueyubo <br>
 * @date 2024-06-05 21:35
 */
public class FileFindVisitor extends Visitor{
    private final String filetype;
    private final ArrayList<Entry> found = new ArrayList<>();

    public FileFindVisitor(String filetype) { // 指定.后面的文件后缀名，如".txt"
        this.filetype = filetype;
    }

    public Iterator<Entry> getFoundFiles() {  // 获取已经找到的文件
        return found.iterator();
    }

    @Override
    public void visit(File file) {
        if (file.getName().endsWith(filetype)) {
            found.add(file);
        }
    }

    @Override
    public void visit(Directory directory) {
        Iterator<Entry> it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = it.next();
            entry.accept(this);
        }
    }
}
