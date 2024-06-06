package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility03;

/**
 * @author yueyubo <br>
 * @date 2024-06-06 22:29
 */
public class LimitSupport extends Support {
    // 可以解决编号小于limit的问题
    private final int limit;

    public LimitSupport(String name, int limit) {   // 构造函数
        super(name);
        this.limit = limit;
    }

    protected boolean resolve(Trouble trouble) {    // 解决问题的方法
        return trouble.getNumber() < limit;
    }
}
