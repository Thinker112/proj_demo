package cn.yyb.creational.simpleFactory02.idcard;

import cn.yyb.creational.simpleFactory02.framework.Factory;
import cn.yyb.creational.simpleFactory02.framework.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yueyubo <br>
 * @date 2024-05-27 21:20
 */
public class IDCardFactory extends Factory {

    private Map<String, String> owners = new HashMap();

    @Override
    protected Product createProduct(String owner, String id) {
        return new IDCard(owner, id);
    }

    @Override
    protected void registerProduct(Product product) {
        owners.put(((IDCard)product).getOwner(), ((IDCard)product).getId());
    }
}
