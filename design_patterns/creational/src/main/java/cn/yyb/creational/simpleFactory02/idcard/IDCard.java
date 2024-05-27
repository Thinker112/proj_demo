package cn.yyb.creational.simpleFactory02.idcard;

import cn.yyb.creational.simpleFactory02.framework.Product;

/**
 * @author yueyubo <br>
 * @date 2024-05-27 21:18
 */
public class IDCard extends Product {

    private String owner;

    private String id;

    IDCard(String owner, String id) {
        System.out.println("制作" + owner + " 的ID Card");
        this.owner = owner;
        this.id = id;
    }

    @Override
    public void use() {
        System.out.println("使用" + owner + " 的ID Card");
    }

    public String getOwner() {
        return owner;
    }

    public String getId() {
        return id;
    }
}
