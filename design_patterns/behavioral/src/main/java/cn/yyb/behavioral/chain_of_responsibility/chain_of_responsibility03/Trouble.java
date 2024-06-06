package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility03;

/**
 * 表示发生问题的类
 * @author yueyubo <br>
 * @date 2024-06-06 22:23
 */
public class Trouble {
    // 问题编号
    private final int number;

    public Trouble(int number) {    // 生成问题
        this.number = number;
    }

    public int getNumber() {        // 获取问题编号
        return number;
    }

    public String toString() {      // 代表问题的字符串
        return "[Trouble " + number + "]";
    }
}
