package cn.yyb.behavioral.observer.observer05;

/**
 * @author yueyubo
 * @date 2024-06-11
 */
public class Main {
//    public static void main(String[] args) {
//        NumberGenerator generator = new RandomNumberGenerator();
//        Observer observer1 = new DigitObserver();
//        Observer observer2 = new GraphObserver();
//        generator.addObserver(observer1);
//        generator.addObserver(observer2);
//        generator.execute();
//    }

    public static void main(String[] args) {
        NumberGenerator generator = new IncrementalNumberGenerator(10, 50, 5);

        Observer observer1 = new DigitObserver();
        Observer observer2 = new GraphObserver();
        Observer observer3 = new ConcreteObserver();

        generator.addObserver(observer1);
        generator.addObserver(observer2);
        generator.addObserver(observer3);

        generator.execute();
    }
}
