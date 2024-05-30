package cn.yyb.creational.prototype01.framework;

/**
 * Prototype(定义用户复制现有实例来生成新实例的方法。）
 * @author yueyubo <br>
 * @date 2024-05-30 21:22
 */
public abstract class Product implements Cloneable {
    public abstract void use(String s);

     Product createClone(){
         Product product = null;
         try {
             product = (Product) clone();
         } catch (CloneNotSupportedException e){
             e.printStackTrace();
         }
         return product;
     }
}
