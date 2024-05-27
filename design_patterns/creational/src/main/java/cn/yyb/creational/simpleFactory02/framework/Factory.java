package cn.yyb.creational.simpleFactory02.framework;

/**
 * 模板工厂
 * @author yueyubo <br>
 * @date 2024-05-27 21:15
 */
public abstract class Factory {

    public final Product create(String owner, String id){
        Product product = createProduct(owner, id);
        registerProduct(product);
        return product;
    }

    protected abstract Product createProduct(String owner, String id);
    protected abstract void registerProduct(Product product);

}
