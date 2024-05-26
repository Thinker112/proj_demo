package cn.yyb.structural.adapter.adapter02;

/**
 * 实际情况
 * @author yueyubo <br>
 * @date 2024-05-26 14:18
 */
public class Banner {
    private String string;

    public Banner(String string) {
        this.string = string;
    }

    public void showWithParen(){
        System.out.println("(" + string + ")");
    }

    public void showWithAster(){
        System.out.println("*" + string + "*");
    }


}
