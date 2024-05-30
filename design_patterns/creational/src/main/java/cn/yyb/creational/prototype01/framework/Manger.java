package cn.yyb.creational.prototype01.framework;

import java.util.HashMap;

/**
 * @author yueyubo <br>
 * @date 2024-05-30 21:25
 */
public class Manger {
    private final HashMap<String, Product> showcase = new HashMap<>();

    public void register(String name, Product proto) {
        showcase.put(name, proto);
    }

    public Product create(String protoName) {
        Product product = showcase.get(protoName);
        return product.createClone();
    }
}
