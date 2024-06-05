package cn.yyb.behavioral.visitor.visitor01;

import java.util.Iterator;

/**
 * 访问数据结构并显示元素
 * @author yueyubo <br>
 * @date 2024-06-05 20:42
 */
public class ListVisitor extends Visitor {
    private String currentdir = ""; // 当前访问的文件夹的名字

    public void visit(File file) {  // 在访问文件时被调用
        System.out.println(currentdir + "/" + file);
    }

    public void visit(Directory directory) {   // 在访问文件夹时被调用
        System.out.println(currentdir + "/" + directory);
        String savedir = currentdir;
        currentdir = currentdir + "/" + directory.getName();
        Iterator<Entry> it = directory.iterator();
        //这里是递归调用，略复杂
        while (it.hasNext()) {
            Entry entry = it.next();
            entry.accept(this);
        }
        currentdir = savedir;
    }
}