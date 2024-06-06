package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility03;

/**
 * @author yueyubo <br>
 * @date 2024-06-06 22:31
 */
public class SpecialSupport extends Support {
    private final int number;                                 // 只能解决指定编号的问题

    public SpecialSupport(String name, int number) {    // 构造函数
        super(name);
        this.number = number;
    }

    protected boolean resolve(Trouble trouble) {        // 解决问题的方法
        return trouble.getNumber() == number;
    }
}