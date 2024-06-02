package cn.yyb.structural.bridge.brideg01;

/**
 * 类的功能层次结构
 * @author yueyubo <br>
 * @date 2024-06-02 17:52
 */
public class CountDisplay extends Display {

    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }

    public void multiDisplay(int times){
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}
