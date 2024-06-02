package cn.yyb.structural.bridge.brideg01;

/**
 * 桥接模式-将类的功能层次结构与实现层次结构分离
 * @author yueyubo <br>
 * @date 2024-06-02 17:47
 */
public class Display {

    private final DisplayImpl impl;

    public Display(DisplayImpl impl) {
        this.impl = impl;
    }

    public void open(){
        impl.rawOpen();
    }

    public void print(){
        impl.rawPrint();
    }

    public void close(){
        impl.rawClose();
    }

    public void display() {
        open();
        print();
        close();
    }
}
