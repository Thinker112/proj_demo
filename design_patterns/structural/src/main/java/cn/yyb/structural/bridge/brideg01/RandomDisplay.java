package cn.yyb.structural.bridge.brideg01;

import java.util.Random;

/**
 * @author yueyubo <br>
 * @date 2024-06-02 18:18
 */
public class RandomDisplay extends CountDisplay {
    public RandomDisplay(DisplayImpl impl) {
        super(impl);
    }

    public void randomDisplay(int times) {
        int time = new Random().nextInt(times);
        open();
        for (int i = 0; i < time; i++) {
            print();
        }
        close();
    }

}
