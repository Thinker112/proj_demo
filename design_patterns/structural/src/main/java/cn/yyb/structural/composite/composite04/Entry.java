package cn.yyb.structural.composite.composite04;

/**
 * 表示目录条目的抽象类
 * @author yueyubo <br>
 * @date 2024-06-04 20:39
 */
public abstract class Entry {
    // 获取名字
    public abstract String getName();
    // 获取大小
    public abstract int getSize();
    //上级目录
    protected Entry parent;

    // 加入目录条目
    public Entry add(Entry entry) throws FileTreatmentException {
        throw new FileTreatmentException();
    }

    // 为一览加上前缀并显示目录条目一览
    public void printList() {
        printList("");
    }

    protected abstract void printList(String prefix);

    // 为一览加上前缀
    public String toString() {                                      // 显示代表类的文字
        return getName() + " (" + getSize() + ")";
    }


    public String getFullName(){
        StringBuilder fullName = new StringBuilder();
        Entry entry = this;
        while (entry != null) {
            fullName.insert(0, "/" + entry.getName());
            entry = entry.parent;
        }

        return fullName.toString();
    }
}
