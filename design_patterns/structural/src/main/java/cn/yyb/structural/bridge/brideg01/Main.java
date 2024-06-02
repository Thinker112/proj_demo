package cn.yyb.structural.bridge.brideg01;

/**
 * @author yueyubo <br>
 * @date 2024-06-02 18:03
 */
public class Main {
    public static void main(String[] args) {
//        Display d1 = new Display(new StringDisplayImpl("Hello, World"));
//        CountDisplay d2 = new CountDisplay(new StringDisplayImpl("Hello, China"));
//        RandomDisplay d3 = new RandomDisplay(new StringDisplayImpl("Hello, you"));
//
//        d1.display();
//        d2.display();
//        d3.display();

//        d3.randomDisplay(5);

        Display display = new Display(new TextDisplayImpl("./new.txt"));
        display.display();
    }
}
