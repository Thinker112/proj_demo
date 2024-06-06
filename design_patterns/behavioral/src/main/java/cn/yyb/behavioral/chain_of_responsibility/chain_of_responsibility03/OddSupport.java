package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility03;

/**
 * @author yueyubo <br>
 * @date 2024-06-06 22:30
 */
public class OddSupport extends Support {
    public OddSupport(String name) {                // 构造函数
        super(name);
    }

    protected boolean resolve(Trouble trouble) {    // 解决问题的方法
        return trouble.getNumber() % 2 == 1;
    }
}