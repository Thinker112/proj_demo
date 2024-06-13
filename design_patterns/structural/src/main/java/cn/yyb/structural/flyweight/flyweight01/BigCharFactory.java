package cn.yyb.structural.flyweight.flyweight01;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyubo
 * @date 2024-06-13
 */
public class BigCharFactory {
    // 管理已经生成的BigChar的实例
    private final Map<String, BigChar> pool = new HashMap<>();
    // Singleton模式
    private static final BigCharFactory singleton = new BigCharFactory();

    // 构造函数
    private BigCharFactory() {
    }

    // 获取唯一的实例
    public static BigCharFactory getInstance() {
        return singleton;
    }

    // 生成（共享）BigChar类的实例
    public synchronized BigChar getBigChar(char charname) {
        BigChar bc = pool.get("" + charname);
        if (bc == null) {
            bc = new BigChar(charname); // 生成BigChar的实例
            pool.put("" + charname, bc);
        }
        return bc;
    }
}
