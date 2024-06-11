package cn.yyb.behavioral.observer.observer05;

/**
 * @author yueyubo
 * @date 2024-06-11
 */
public class IncrementalNumberGenerator extends NumberGenerator {
    private int initNum;
    private int endNum;
    private int addNum;
    private int currentNum;

    public IncrementalNumberGenerator(int initNum, int endNum, int addNum) {
        this.initNum = initNum;
        this.endNum = endNum;
        this.addNum = addNum;
    }

    @Override
    public int getNumber() {
        return currentNum;
    }

    @Override
    public void execute() {
        for (int i = initNum; i < endNum; i+=addNum) {
            currentNum = i;
            notifyObservers();
        }
    }
}
